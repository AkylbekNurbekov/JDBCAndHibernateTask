package peaksoft.service;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoHibernateImpl();
//    UserDao userDao = new UserDaoJdbcImpl();

    public void createUsersTable() {
        userDao.createUsersTable();

    }

    public void dropUsersTable() throws SQLException {
        userDao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDao.saveUser(name,lastName,age);

    }

    public void removeUserById(long id) throws SQLException {
        userDao.removeUserById(id);

    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        userDao.cleanUsersTable();
    }

}
