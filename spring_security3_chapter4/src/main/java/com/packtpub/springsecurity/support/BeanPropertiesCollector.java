package com.packtpub.springsecurity.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class BeanPropertiesCollector implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		/*
		 ** collect FileChain **
			FilterChainProxy b = beanFactory.getBean(FilterChainProxy.class);
			SecurityFilterChain sfc = b.getFilterChains().get(0);
			List<Filter> filters = sfc.getFilters();
			System.out.println("FilterChain :");
			int index = 0;
			for(Filter filter : filters){
				System.out.println("\t |-- " + ++index + " : " +  filter.getClass());
			}
		
		String[] customJdbcDaoImpls = beanFactory.getBeanNamesForType(CustomJdbcDaoImpl.class);
		String beanName = customJdbcDaoImpls[0];
		BeanDefinition beanDefinitionOfCJDI = beanFactory.getBeanDefinition(beanName);
		System.out.println(beanDefinitionOfCJDI);
		 */
	}

}
