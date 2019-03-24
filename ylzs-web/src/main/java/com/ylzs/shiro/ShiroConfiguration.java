package com.ylzs.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * shrio配置类
 */
@Configuration
public class ShiroConfiguration implements EnvironmentAware{
//	private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);	
	
	private RelaxedPropertyResolver propertyResolver;
	
	@Override
	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment,"shiro.");		
	}

	/**
	 * 自定义的session共享类
	 * @return
	 */
	@Bean(name="shiroSessionRedis")
	public ShiroSessionRedis getShiroSessionRedis(){
		return new ShiroSessionRedis();
	}
	
	/**
	 * 自定义的认证类，继承自AuthorizingRealm，负责用户的认证和权限的处理
	 * @return
	 */
	@Bean(name = "ShiroRealmImpl")
	public ShiroRealmImpl getShiroRealm() {
		return new ShiroRealmImpl();
	}

	/**
	 * 缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来.
	 * 然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库
	 * @return
	 */
	@Bean(name = "shiroEhcacheManager")
	public EhCacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile(propertyResolver.getProperty("cacheManagerConfigFile"));
		return em;
	}
	
	/**
	 * 设置sessionManager
	 * @return
	 */
	@Bean(name = "sessionManager")
	public DefaultWebSessionManager getDefaultWebSessionManager(){
		DefaultWebSessionManager dwsm = new DefaultWebSessionManager();
		dwsm.setSessionDAO(getShiroSessionRedis());
		dwsm.setGlobalSessionTimeout(Long.parseLong(propertyResolver.getProperty("globalSessionTimeout")));
		return dwsm;
	}
	
	/**
	 * 权限管理，组合了登陆，登出，权限，session的处理
	 * @return
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager() {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setSessionManager(getDefaultWebSessionManager());//设置WebSessionManager
		dwsm.setRealm(getShiroRealm());
		dwsm.setCacheManager(getEhCacheManager());
		return dwsm;
	}
	
	/**
	 * factorybean，为了生成ShiroFilter
	 * 主要保持了三项数据:securityManager，filters，filterChainDefinitionManager
	 * @return
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());//必须设置 SecurityManager 
		shiroFilterFactoryBean.setLoginUrl(propertyResolver.getProperty("loginUrl"));
		shiroFilterFactoryBean.setSuccessUrl(propertyResolver.getProperty("successUrl"));
		shiroFilterFactoryBean.setUnauthorizedUrl(propertyResolver.getProperty("unauthorizedUrl"));
		loadShiroFilterChain(shiroFilterFactoryBean);
		return shiroFilterFactoryBean;
	}
	
	/**
	 * 加载shiroFilter权限控制规则（从数据库读取然后配置）
	 * @param shiroFilterFactoryBean
	 */
	private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/**", "anon");////anon 不拦截
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/user/**", "authc,roles[user]");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
	}

	/**
	 * 注册DelegatingFilterProxy
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		//缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理  
		filterRegistration.addInitParameter("targetFilterLifecycle", "true");
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");
		return filterRegistration;
	}
	
	/**
	 * Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
	 * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}
	
	/**
	 * shiro里实现的Advisor类，内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法
	 *  RequiresPermissions.class, RequiresRoles.class,
     *  RequiresUser.class, RequiresGuest.class, RequiresAuthentication.class
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(getDefaultWebSecurityManager());
		return new AuthorizationAttributeSourceAdvisor();
	}
	
	/**
	 * DestructionAwareBeanPostProcessor的子类，
	 * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
	 * 主要是AuthorizingRealm类的子类，以及EhCacheManager类
	 * @return
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
}
