package com.golubeva.project.service.impl;

import com.golubeva.project.controller.RequestParameter;
import com.golubeva.project.dao.UserDao;
import com.golubeva.project.dao.impl.UserDaoImpl;
import com.golubeva.project.entity.User;
import com.golubeva.project.exception.DaoException;
import com.golubeva.project.exception.ServiceException;
import com.golubeva.project.service.UserService;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class UserServiceImplTest {
    private UserDao userDao;
    private UserService userService;

    @BeforeClass
    public void setUp() {
        userDao = mock(UserDaoImpl.class);
        Whitebox.setInternalState(UserDaoImpl.class, "INSTANCE", userDao);
        userService = new UserServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        userDao = null;
        userService = null;
    }

    @Test
    public void addUserPositiveTest() {
        try {
            when(userDao.findByEmail(any(String.class))).thenReturn(Optional.empty());
            when(userDao.add(any(User.class), any(String.class))).thenReturn(true);
            Map<String, String> actual = new HashMap<>();
            actual.put(RequestParameter.EMAIL, "example@gmail.com");
            actual.put(RequestParameter.NAME, "Name");
            actual.put(RequestParameter.SURNAME, "Surname");
            actual.put(RequestParameter.PATRONYMIC, "Patronymic");
            actual.put(RequestParameter.PASSWORD, "12345a");
            actual.put(RequestParameter.PASSWORD_REPEAT, "12345a");
            Map<String, String> expected = new HashMap<>();
            expected.put(RequestParameter.EMAIL, "example@gmail.com");
            expected.put(RequestParameter.NAME, "Name");
            expected.put(RequestParameter.SURNAME, "Surname");
            expected.put(RequestParameter.PATRONYMIC, "Patronymic");
            expected.put(RequestParameter.PASSWORD, "12345a");
            expected.put(RequestParameter.PASSWORD_REPEAT, "12345a");
            userService.addUser(actual);
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void addUserNegativeTest() {
        try {
            when(userDao.findByEmail(any(String.class))).thenReturn(Optional.empty());
            when(userDao.add(any(User.class), any(String.class))).thenReturn(true);
            Map<String, String> actual = new HashMap<>();
            actual.put(RequestParameter.EMAIL, "user@gmail.com");
            actual.put(RequestParameter.NAME, "Name");
            actual.put(RequestParameter.SURNAME, "Surname");
            actual.put(RequestParameter.PATRONYMIC, "Patronymic");
            actual.put(RequestParameter.PASSWORD, "12345a");
            actual.put(RequestParameter.PASSWORD_REPEAT, "12345a");
            Map<String, String> expected = new HashMap<>();
            expected.put(RequestParameter.EMAIL, "");
            expected.put(RequestParameter.NAME, "Name");
            expected.put(RequestParameter.SURNAME, "Surname");
            expected.put(RequestParameter.PATRONYMIC, "Patronymic");
            expected.put(RequestParameter.PASSWORD, "12345a");
            expected.put(RequestParameter.PASSWORD_REPEAT, "12345a");
            userService.addUser(actual);
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void changePasswordPositiveTest() {
        try {
            when(userDao.changePassword(any(String.class), any(String.class))).thenReturn(true);
            String email = "example@gmail.com";
            String password = "qwerty1";
            String passwordRepeat = "qwerty1";
            boolean actual = userService.changePassword(email, password, passwordRepeat);
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void changePasswordNegativeTest() {
        try {
            when(userDao.changePassword(any(String.class), any(String.class))).thenReturn(true);
            String email = "example@gmail.com";
            String password = "qwerty";
            String passwordRepeat = "qwerty1";
            boolean actual = userService.changePassword(email, password, passwordRepeat);
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findByIdPositiveTest() {
        try {
            User expected = new User();
            when(userDao.findById(any(Integer.class))).thenReturn(Optional.of(new User()));
            Optional<User> actual = userService.findUserById("1");
            assertEquals(actual.get(), expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findByIdNegativeTest() {
        try {
            Optional<User> expected = Optional.of(new User());
            when(userDao.findById(any(Integer.class))).thenReturn(Optional.of(new User()));
            Optional<User> actual = userService.findUserById(" ");
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findByEmailPositiveTest() {
        try {
            User expected = new User();
            when(userDao.findByEmail(any(String.class))).thenReturn(Optional.of(new User()));
            Optional<User> actual = userService.findUserByEmail("example@gmail.com");
            assertEquals(actual.get(), expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findByEmailNegativeTest() {
        try {
            Optional<User> expected = Optional.of(new User());
            when(userDao.findByEmail(any(String.class))).thenReturn(Optional.of(new User()));
            Optional<User> actual = userService.findUserById(" ");
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findByAccessCodePositiveTest() {
        try {
            User user = new User();
            user.setUserId(1);
            List<User> userList = new ArrayList<>();
            userList.add(user);
            Optional<User> expected = Optional.of(user);
            Optional<User> actual = userService.findUserByAccessCode("8694a38a77ff62fc96ad3b4cfba224c9a78eeb59db" +
                    "c8fc3e1a739344e0d978a0f68e12e0ba2496e790de8e0ae89994ad6b7e658e7cf5631435721e5059b6437d", userList);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findByAccessCodeNegativeTest() {
        try {
            User user = new User();
            user.setUserId(1);
            List<User> userList = new ArrayList<>();
            userList.add(user);
            Optional<User> expected = Optional.of(user);
            Optional<User> actual = userService.findUserByAccessCode("code", userList);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void removePositiveTest() {
        try {
            when(userDao.remove(any(String.class))).thenReturn(true);
            boolean actual = userService.removeUser("admin@gmail.com");
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void removeNegativeTest() {
        try {
            when(userDao.remove(any(String.class))).thenReturn(true);
            boolean actual = userService.removeUser(" ");
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void blockPositiveTest() {
        try {
            when(userDao.block(any(String.class))).thenReturn(true);
            boolean actual = userService.blockUser("admin@gmail.com");
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void blockNegativeTest() {
        try {
            when(userDao.block(any(String.class))).thenReturn(true);
            boolean actual = userService.blockUser(" ");
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void unblockPositiveTest() {
        try {
            when(userDao.unblock(any(String.class))).thenReturn(true);
            boolean actual = userService.unblockUser("admin@gmail.com");
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void unblockNegativeTest() {
        try {
            when(userDao.unblock(any(String.class))).thenReturn(true);
            boolean actual = userService.unblockUser(" ");
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void fillUpBalancePositiveTest() {
        try {
            when(userDao.findById(any(Integer.class))).thenReturn(Optional.of(new User()));
            when(userDao.update(any(User.class))).thenReturn(true);
            boolean actual = userService.fillUpBalance("1", "100");
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void fillUpBalanceNegativeTest() {
        try {
            when(userDao.findById(any(Integer.class))).thenReturn(Optional.of(new User()));
            when(userDao.update(any(User.class))).thenReturn(true);
            boolean actual = userService.fillUpBalance(" ", " ");
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void activateUserPositiveTest() {
        try {
            when(userDao.findByEmail(any(String.class))).thenReturn(Optional.of(new User()));
            when(userDao.update(any(User.class))).thenReturn(true);
            boolean actual = userService.activateUser("admin@gmail.com");
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void activateUserNegativeTest() {
        try {
            when(userDao.findByEmail(any(String.class))).thenReturn(Optional.of(new User()));
            when(userDao.update(any(User.class))).thenReturn(true);
            boolean actual = userService.activateUser(" ");
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findAllPositiveTest() {
        try {
            List<User> expected = new ArrayList<>();
            when(userDao.findAll()).thenReturn(new ArrayList<>());
            List<User> actual = userService.findAllUsers();
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findAllNegativeTest() {
        try {
            List<User> expected = null;
            when(userDao.findAll()).thenReturn(new ArrayList<>());
            List<User> actual = userService.findAllUsers();
            assertNotEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findBySearchQueryPositiveTest() {
        try {
            List<User> expected = new ArrayList<>();
            when(userDao.findBySearchQuery(any(String.class))).thenReturn(new ArrayList<>());
            List<User> actual = userService.findUsersBySearchQuery("1");
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findBySearchQueryNegativeTest() {
        try {
            List<User> expected = null;
            when(userDao.findBySearchQuery(any(String.class))).thenReturn(new ArrayList<>());
            List<User> actual = userService.findUsersBySearchQuery("1");
            assertNotEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void isUserExistsPositiveTest() {
        try {
            when(userDao.findByEmailAndPassword(any(String.class), any(String.class)))
                    .thenReturn(Optional.of(new User()));
            boolean actual = userService.isUserExists("admin@gmail.com", "password1");
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void isUserExistsNegativeTest() {
        try {
            when(userDao.findByEmailAndPassword(any(String.class), any(String.class)))
                    .thenReturn(Optional.of(new User()));
            boolean actual = userService.isUserExists(" ", " ");
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }
}