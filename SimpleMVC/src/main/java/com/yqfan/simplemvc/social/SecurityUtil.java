package com.yqfan.simplemvc.social;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.yqfan.simplemvc.model.MyUser;

public class SecurityUtil {
	public static Authentication signInUser(MyUser user) {
		// sign in the userId, password is null
	    Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUserName(), null, null);
	    System.out.println("SecurityUtil is called, principal name="+authentication.getName());
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    return authentication;
	}
}
