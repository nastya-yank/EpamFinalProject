package com.golubeva.project.service.impl;

import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.exception.DaoException;
import com.golubeva.project.exception.SendMailException;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.service.UserService;
import com.golubeva.project.validator.UserValidator;
import com.golubeva.project.dao.UserDao;
import com.golubeva.project.dao.impl.UserDaoImpl;
import com.golubeva.project.entity.User;
import com.golubeva.project.util.CustomCipher;
import com.golubeva.project.util.mail.MailSender;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The {@code UserServiceImpl} class represents user service implementation.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class UserServiceImpl implements UserService {
    private static final String EMPTY_VALUE = "";
    private final UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public boolean changePassword(String email, String password, String passwordRepeat) throws ServiceException {
        boolean result = false;
        try {
            if (UserValidator.isEmailValid(email) && UserValidator.isPasswordValid(password)
                    && password.equals(passwordRepeat)) {
                CustomCipher cipher = new CustomCipher();
                String encryptedPassword = cipher.encrypt(password);
                result = userDao.changePassword(email, encryptedPassword);
            }
        } catch (DaoException | NoSuchAlgorithmException e) {
            throw new ServiceException("Error while changing password", e);
        }
        return result;
    }

    @Override
    public Optional<User> findUserById(String id) throws ServiceException {
        Optional<User> optionalUser = Optional.empty();
        try {
            if (UserValidator.isIdValid(id)) {
                int userId = Integer.parseInt(id);
                optionalUser = userDao.findById(userId);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding user by id", e);
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        Optional<User> optionalUser;
        try {
            optionalUser = userDao.findByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException("Error while getting user by email", e);
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findUserByAccessCode(String code, List<User> userList) throws ServiceException {
        Optional<User> optionalUser = Optional.empty();
        CustomCipher cipher = new CustomCipher();
        try {
            for (User user : userList) {
                if (cipher.encrypt(user.toString()).equals(code)) {
                    optionalUser = Optional.of(user);
                    break;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Error while finding user by access code", e);
        }
        return optionalUser;
    }

    @Override
    public boolean removeUser(String email) throws ServiceException {
        boolean isRemoved = false;
        try {
            if (UserValidator.isEmailValid(email)) {
                isRemoved = userDao.remove(email);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while removing user by email", e);
        }
        return isRemoved;
    }

    @Override
    public boolean blockUser(String email) throws ServiceException {
        boolean isBlocked = false;
        try {
            if (UserValidator.isEmailValid(email)) {
                isBlocked = userDao.block(email);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while blocking user", e);
        }
        return isBlocked;
    }

    @Override
    public boolean unblockUser(String email) throws ServiceException {
        boolean isUnblocked = false;
        try {
            if (UserValidator.isEmailValid(email)) {
                isUnblocked = userDao.unblock(email);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while unblocking user", e);
        }
        return isUnblocked;
    }

    @Override
    public boolean fillUpBalance(String userId, String moneyAmount) throws ServiceException {
        boolean result = false;
        try {
            if (UserValidator.isIdValid(userId) && UserValidator.isPriceValid(moneyAmount)) {
                double amount = Double.parseDouble(moneyAmount);
                int userIdParsed = Integer.parseInt(userId);
                Optional<User> optionalUser = userDao.findById(userIdParsed);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    double newBalance = user.getBalance() + amount;
                    user.setBalance(newBalance);
                    userDao.update(user);
                    result = true;
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while filling up user balance", e);
        }
        return result;
    }

    @Override
    public void sendLetter(User user, String url) throws ServiceException {
        try {
            CustomCipher cipher = new CustomCipher();
            String code = cipher.encrypt(user.toString());
            MailSender sender = new MailSender(user.getEmail(), url, code);
            sender.send();
        } catch (SendMailException | NoSuchAlgorithmException e) {
            throw new ServiceException("Error while sending message", e);
        }
    }

    @Override
    public boolean activateUser(String email) throws ServiceException {
        boolean result = false;
        try {
            if (UserValidator.isEmailValid(email)) {
                Optional<User> optionalUser = userDao.findByEmail(email);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    user.setStatus(User.Status.ENABLE);
                    if (userDao.update(user)) {
                        result = true;
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while updating user", e);
        }
        return result;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error while finding all users", e);
        }
    }

    @Override
    public List<User> findUsersBySearchQuery(String searchQuery) throws ServiceException {
        try {
            return userDao.findBySearchQuery(searchQuery);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding users by search query", e);
        }
    }


    @Override
    public boolean isUserExists(String email, String password) throws ServiceException {
        boolean result = false;
        try {
            if (UserValidator.isEmailValid(email) && UserValidator.isPasswordValid(password)) {
                CustomCipher cipher = new CustomCipher();
                String encryptedPassword = cipher.encrypt(password);
                Optional<User> optionalUser = userDao.findByEmailAndPassword(email, encryptedPassword);
                result = optionalUser.isPresent();
            }
        } catch (DaoException | NoSuchAlgorithmException e) {
            throw new ServiceException("Error while checking user for presence in database", e);
        }
        return result;
    }

    @Override
    public boolean addUser(Map<String, String> parameters) throws ServiceException {
        boolean result = false;
        String email = parameters.get(RequestParameter.EMAIL);
        String password = parameters.get(RequestParameter.PASSWORD);
        String name = parameters.get(RequestParameter.NAME);
        String surname = parameters.get(RequestParameter.SURNAME);
        String patronymic = parameters.get(RequestParameter.PATRONYMIC);
        if (UserValidator.isRegistrationParametersCorrect(parameters)) {
            if (isEmailFree(email)) {
                try {
                    String encryptedPassword = CustomCipher.encrypt(password);
                    User user = new User();
                    user.setEmail(email);
                    user.setName(name);
                    user.setSurname(surname);
                    user.setPatronymic(patronymic);
                    user.setStatus(User.Status.NOT_CONFIRMED);
                    user.setRole(User.Role.USER);
                    user.setBalance(0);
                    result = userDao.add(user, encryptedPassword);
                } catch (DaoException | NoSuchAlgorithmException e) {
                    throw new ServiceException("Error while adding user", e);
                }
            } else {
                parameters.put(RequestParameter.EMAIL, EMPTY_VALUE);
            }
        }
        return result;
    }

    private boolean isEmailFree(String email) throws ServiceException {
        boolean result = false;
        try {
            if (UserValidator.isEmailValid(email)) {
                Optional<User> optionalUser = userDao.findByEmail(email);
                result = optionalUser.isEmpty();
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while checking free email for presence in database", e);
        }
        return result;
    }
}
