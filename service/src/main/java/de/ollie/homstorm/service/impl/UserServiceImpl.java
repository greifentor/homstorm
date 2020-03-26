package de.ollie.homstorm.service.impl;

import javax.inject.Named;

import de.ollie.homstorm.service.UserService;

/**
 * An implementation for the user service.
 *
 * @author ollie (26.03.2020)
 */
@Named
public class UserServiceImpl implements UserService {

	@Override
	public boolean isAccepted(String userName, String password) {
		return userName.equals("user") && password.equals("ncc1701");
	}

}