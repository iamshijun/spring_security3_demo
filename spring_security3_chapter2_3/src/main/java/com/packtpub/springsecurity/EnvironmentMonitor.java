package com.packtpub.springsecurity;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

public class EnvironmentMonitor implements ApplicationContextAware{

	private  ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	@PostConstruct
	public void afterPropertiesSet(){
		System.out.println("EnvironmentMonitor.afterPropertiesSet()");
		System.out.println("==============================================================");
		Map<String, UserDetailsService> beansOfUserDetailsService = applicationContext.getBeansOfType(UserDetailsService.class);
		for(Map.Entry<String, UserDetailsService> uds : beansOfUserDetailsService.entrySet()){
			System.out.println(uds.getValue().getClass());
		}
		
		Map<String, AuthenticationProvider> beansOfAuthenticationProvider = applicationContext.getBeansOfType(AuthenticationProvider.class);
		for(Map.Entry<String, AuthenticationProvider> uds : beansOfAuthenticationProvider.entrySet()){
			System.out.println(uds.getValue().getClass());
		}
		System.out.println("==============================================================");
	}
	
}
