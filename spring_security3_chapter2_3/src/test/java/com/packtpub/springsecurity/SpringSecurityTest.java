package com.packtpub.springsecurity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/*.xml")
public class SpringSecurityTest {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Before
	public void setUp(){
		System.out.println("=====================Test start=========================");
	}
	@After
	public void tearDown(){
		System.out.println("=====================Test end=========================");
	}
	
	@Test
	public void testDummy(){}
	
	@Test
	public void testAuthenticationManager(){
		System.out.println();
		
		Map<String, AuthenticationManager> beansOfAM = applicationContext.getBeansOfType(AuthenticationManager.class);
		
		Map<AuthenticationManager,List<String>> beansNAlias = new HashMap<>(); 
		for(Map.Entry<String, AuthenticationManager> entryOfAM : beansOfAM.entrySet()){
			String alias = entryOfAM.getKey();
			AuthenticationManager am = entryOfAM.getValue();
			if(beansNAlias.containsKey(am)){
				beansNAlias.get(am).add(alias);
			}else{
				List<String> names = new LinkedList<>();
				beansNAlias.put(am, names);
			}
		}
		
		 AuthenticationManager authenticationManager = applicationContext.getBean(BeanIds.AUTHENTICATION_MANAGER,AuthenticationManager.class);
		 ProviderManager pm = (ProviderManager) authenticationManager;
		 
		 List<AuthenticationProvider> providers = pm.getProviders();
		 System.out.println(providers);
		 Assertions.assertThat(providers).hasSize(1);
		 
		 Assertions
		 	.assertThat(beansNAlias)
		 	.hasSize(2)
		 	.containsKey(authenticationManager)
		 ;
		 
		 System.out.println();
	}
	
	@Test
	public void testUserDetailsService(){
//		InMemoryUserDetailsManager
		/*InMemoryUserDetailsManager inMemoryUserService = applicationContext.getBean(InMemoryUserDetailsManager.class);
		UserDetails loadUserByUsername = inMemoryUserService.loadUserByUsername("guest");
		Assertions.assertThat(loadUserByUsername).isInstanceOf(User.class);
		User user = (User) loadUserByUsername;
		Assertions.assertThat(user.getPassword()).isEqualTo("guest");*/
		
		Object userService = applicationContext.getBean("userService");
		System.out.println("Bean name 'userService' : " + userService.getClass());
		
		Map<String, UserDetailsService> beansOfUserDetailsService = applicationContext.getBeansOfType(UserDetailsService.class);
		for(Map.Entry<String, UserDetailsService> uds : beansOfUserDetailsService.entrySet()){
			System.out.println(uds.getValue().getClass());
		}
		
		
  		Map<String, AuthenticationProvider> beansOfAuthenticationProvider = applicationContext.getBeansOfType(AuthenticationProvider.class);
		for(Map.Entry<String, AuthenticationProvider> uds : beansOfAuthenticationProvider.entrySet()){
			System.out.println(uds.getValue().getClass());
		}
	}
}
