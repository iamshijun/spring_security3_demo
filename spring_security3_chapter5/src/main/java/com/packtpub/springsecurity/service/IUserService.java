package com.packtpub.springsecurity.service;

import org.springframework.security.access.prepost.PreAuthorize;



/**
 * Provides functionality related to user management.
 *  
 * @author Mularien
 */
public interface IUserService {
	/**
	 * Changes the password for the given username. Expects that the calling user
	 * is a valid user of the system.
	 * 
	 * @param username the name of the user to change
	 * @param password the password to change to
	 * @throws IllegalAccessException 
	 */
	// Ch 05 using spring's annotation out-of-box
	//@Secured("ROLE_ADMIN")
	// Ch 05 PreAuthorize with hasRole
	//@PreAuthorize("hasRole('ROLE_USER')") // change to ROLE_ADMIN to test authorization
	// Ch 05 PreAuthorize with method parameter binding
	@PreAuthorize("#username == principal.username or  hasRole('ROLE_ADMIN')") //只能是管理员 ,修改自己的用户密码
	// Ch 05 JSR-250 test
	//@RolesAllowed({"ROLE_ADMIN"})  //当前使用的是 tomcat 所以你可以在pom中引入tomcat-annotation-api包,scope=provide,或者直接使用jsr250的包
	public void changePassword(String username, String oldPassword ,String newPassword) throws IllegalAccessException;
}
