package com.ylzs.redis;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport {
		
	@SuppressWarnings("rawtypes")
	@Bean
	 public CacheManager springCacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(3000);
		Map<String, Long> expires = new HashMap<String, Long>();
		cacheManager.setExpires(expires);
		return cacheManager;
	 }

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setConnectionFactory(factory);
		template.afterPropertiesSet();
		return template;
	}
	 @Bean  
	 @Override  
	 public KeyGenerator keyGenerator(){
	 	 return new KeyGenerator() {  
			  @Override  
			  public Object generate(Object o, Method method, Object... objects) {  
					StringBuilder sb = new StringBuilder();  
					sb.append(o.getClass().getName());  
					sb.append(method.getName());  
					for (Object obj : objects) {  
						 sb.append(obj.toString());  
					}  
					return sb.toString();  
			  }  
		 };
	 }
	 
}
