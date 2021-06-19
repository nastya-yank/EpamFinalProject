package com.golubeva.project.dao;

import com.golubeva.project.entity.User;
import com.golubeva.project.exception.DaoException;
import java.util.List;
import java.util.Optional;

/**
 * The {@code UserDao} interface represents user dao.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public interface UserDao {

    /**
     * Find user by email and password.
     *
     * @param email the email
     * @param password the password
     * @return the optional of user
     * @throws DaoException the dao exception
     */
    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;

    /**
     * Find user by email.
     *
     * @param email the email
     * @return the optional of user
     * @throws DaoException the dao exception
     */
    Optional<User> findByEmail(String email) throws DaoException;

    /**
     * Find user by id.
     *
     * @param userId the user index
     * @return the optional of user
     * @throws DaoException the dao exception
     */
    Optional<User> findById(Integer userId) throws DaoException;

    /**
     * Update user.
     *
     * @param user the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(User user) throws DaoException;

    /**
     * Remove user.
     *
     * @param email the email
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean remove(String email) throws DaoException;

    /**
     * Block user.
     *
     * @param email the email
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean block(String email) throws DaoException;

    /**
     * Unblock user.
     *
     * @param email the email
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean unblock(String email) throws DaoException;

    /**
     * Find all users.
     *
     * @return the list users
     * @throws DaoException the dao exception
     */
    List<User> findAll() throws DaoException;

    /**
     * Find by search query.
     *
     * @param searchQuery the search query
     * @return the list of users
     * @throws DaoException the dao exception
     */
    List<User> findBySearchQuery(String searchQuery) throws DaoException;

    /**
     * Change password.
     *
     * @param email the email
     * @param password the password
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean changePassword(String email, String password) throws DaoException;

    /**
     * Add user.
     *
     * @param user the user
     * @param encryptedPassword the encrypted password
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(User user, String encryptedPassword) throws DaoException;
}
