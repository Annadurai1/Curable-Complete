package com.curable.filter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Annadurai S
 *
 */
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
