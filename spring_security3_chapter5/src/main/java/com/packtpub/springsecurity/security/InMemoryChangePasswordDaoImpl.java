package com.packtpub.springsecurity.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.memory.InMemoryDaoImpl;

/**
 * Illustrates an extension to InMemoryDaoImpl which allows password changes.
 * 
 * @author Mularien
 */
@SuppressWarnings("deprecation")
public class InMemoryChangePasswordDaoImpl extends InMemoryDaoImpl implements IChangePassword {
	/* (non-Javadoc)
	 * @see com.packtpub.springsecurity.security.IChangePassword#changePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public void changePassword(String username,String oldPassword, String newPassword) throws IllegalAccessException {
		// get the UserDetails
		User userDetails = (User) getUserMap().getUser(username);
		
		if(!userDetails.getPassword().equals(oldPassword)){
			throw new IllegalAccessException("The oldPassword you enter is not equals to the previous password");
		}
		
		// create a new UserDetails with the new password
		User newUserDetails = new User(userDetails.getUsername(),newPassword,
				userDetails.isEnabled(),userDetails.isAccountNonExpired(),
				userDetails.isCredentialsNonExpired(),
				userDetails.isAccountNonLocked(),userDetails.getAuthorities());
		// add to the map
		getUserMap().addUser(newUserDetails);
	}
}
