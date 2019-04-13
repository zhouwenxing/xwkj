package com.ylzs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({@PropertySource("classpath:conf/business-params.properties")})
public class YlzsBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(YlzsBootApplication.class, args);
	}
	
//	@Bean
//	public EmbeddedServletContainerFactory servletContainer(){
//		TomcatEmbeddedServletContainerFactory tc = new TomcatEmbeddedServletContainerFactory();
//		tc.setPort(8898);
//		return tc;
//	}

}
