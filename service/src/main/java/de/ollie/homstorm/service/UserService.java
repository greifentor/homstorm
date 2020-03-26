package de.ollie.homstorm.service;

/**
 * An interface for a user service.
 *
 * @author ollie (26.03.2020)
 */
public interface UserService {

	boolean isAccepted(String userName, String password);

}