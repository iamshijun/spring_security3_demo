package com.packtpub.springsecurity.service;

import java.util.Collection;

import org.springframework.security.access.prepost.PostFilter;

import com.packtpub.springsecurity.data.Category;

public interface IProductService {
	/**
	 * Get all categories the user has access to.
	 * 
	 * @return the list of available categories
	 */
	// Ch 5 @PostFilter  通过@PostFilter实现基于角色的数据过滤
	//  其中SpEl中的伪属性  filterObject指的是当前返回的集合generic类型  :  Category
	@PostFilter("(!filterObject.customersOnly) or (filterObject.customersOnly and hasRole('ROLE_ADMIN'))")
	Collection<Category> getCategories();

	/**
	 * Finds the category with the given name.
	 * @param name the name of the category to find.
	 * @return the matching category, or null
	 */
	Category getCategoryByName(String name);
}
