package com.packtpub.springsecurity.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationStatus;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class OpenIDAuthenticationFailureHandler extends	SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,	HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		/* 如果当前 使用OpenId养成成功  但是当前的数据库中没有  注册和当前  local identifier (OpenId Provider返回的最终的唯一标识符 很有可能和 用户提供(在表单中输入的)的 标识符 不一致) , 跳转到 registrationOpenId.do */
		if(exception instanceof UsernameNotFoundException 
				&& exception.getAuthentication() instanceof OpenIDAuthenticationToken
				&& ((OpenIDAuthenticationToken)exception.getAuthentication()).getStatus().equals(OpenIDAuthenticationStatus.SUCCESS)) {
			
			DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
			request.getSession(true).setAttribute("USER_OPENID_CREDENTIAL", ((UsernameNotFoundException)exception).getExtraInformation());
			//((UsernameNotFoundException)exception).getExtraInformation() 存储的是  local identifier 唯一标识符(URI)
			// Ch 8 AX Experimentation
			OpenIDAuthenticationToken openIdAuth = (OpenIDAuthenticationToken)exception.getAuthentication();
			
			for(OpenIDAttribute attr : openIdAuth.getAttributes()) {
				System.out.printf("AX Attribute: %s, Type: %s, Count: %d\n", attr.getName(), attr.getType(), attr.getCount());
				for(String value : attr.getValues()) {
					System.out.printf(" Value: %s\n", value);
				}
			}
			// redirect to create account page
			redirectStrategy.sendRedirect(request, response, "/registrationOpenid.do");
		} else {
			super.onAuthenticationFailure(request, response, exception);
		}
	}
	
}
