package com.traulko.project.service;

import com.traulko.project.entity.User;
import com.traulko.project.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The {@code UserService} service represents user service.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public interface UserService {

    /**
     * Check is user exists.
     *
     * @param email the email
     * @param password the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isUserExists(String email, String password) throws ServiceException;

    /**
     * Fill up user balance.
     *
     * @param userId the user index
     * @param moneyAmount the amount of money
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean fillUpBalance(String userId, String moneyAmount) throws ServiceException;

    /**
     * Send letter to user mail.
     *
     * @param user the user
     * @param url the url
     */
    void sendLetter(User user, String url) throws ServiceException;

    /**
     * Activate user.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean activateUser(String email) throws ServiceException;

    List<User> findAllUsers() throws ServiceException;

    List<User> findUsersBySearchQuery(String searchQuery) throws ServiceException;

    /**
     * Change user password.
     *
     * @param email the email
     * @param password the password
     * @param passwordRepeat the password repeat
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changePassword(String email, String password, String passwordRepeat) throws ServiceException;

    /**
     * Find user by id.
     *
     * @param userId the user index
     * @return the optional of user
     * @throws ServiceException the service exception
     */
    Optional<User> findUserById(String userId) throws ServiceException;

    /**
     * Find user by email.
     *
     * @param email the email
     * @return the optional of user
     * @throws ServiceException the service exception
     */
    Optional<User> findUserByEmail(String email) throws ServiceException;

    /**
     * Find user by access code.
     *
     * @param code the code
     * @param userList the user list
     * @return the optional of user
     * @throws ServiceException the service exception
     */
    Optional<User> findUserByAccessCode(String code, List<User> userList) throws ServiceException;

    /**
     * Remove user.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean removeUser(String email) throws ServiceException;

    /**
     * Block user.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean blockUser(String email) throws ServiceException;

    /**
     * Unblock user.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean unblockUser(String email) throws ServiceException;

    /**
     * Add user.
     *
     * @param parameters the parameters
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addUser(Map<String, String> parameters) throws ServiceException;
}
