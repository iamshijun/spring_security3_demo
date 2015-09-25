package com.packtpub.springsecurity.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * The base Spring MVC controller. Used to provide common functionality to all
 * controllers.
 * 
 * @author Mularien
 */
public class BaseController {
	//这个方法添加了@ModelAttribute注解，任何实现BaseController的控制器触发时，Spring MVC将会自动执行此方法。针对authorize标签方式的其它显示/隐藏功能，我们能够很简单地使用模型数据指令重复这种设计模式
	@ModelAttribute("showLoginLink")
	public boolean getShowLoginLink() {
		for (GrantedAuthority authority : getAuthentication().getAuthorities()) {
			if (authority.getAuthority().equals("ROLE_USER")) {
				return false;
			}
		}
		return true;
	}

	protected Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
