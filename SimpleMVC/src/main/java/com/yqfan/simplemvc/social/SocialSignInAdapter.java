package com.yqfan.simplemvc.social;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import com.yqfan.simplemvc.dao.JdbcUserDao;
import com.yqfan.simplemvc.model.MyUser;

public class SocialSignInAdapter implements SignInAdapter {
	
	@Autowired
	JdbcUserDao userdao;
	private static final Logger logger = LoggerFactory.getLogger(SocialSignInAdapter.class);

	@Override
	public String signIn(String username, Connection<?> connection, NativeWebRequest request) {
		logger.info("siginAdapter signin is called, username="+username);
		MyUser user = userdao.findByName(username);
		if (user != null) {
			SecurityUtil.signInUser(user);
		}
		else {
			logger.error("local user id " + username + " not found");
		}
		return "home";
	}

}
