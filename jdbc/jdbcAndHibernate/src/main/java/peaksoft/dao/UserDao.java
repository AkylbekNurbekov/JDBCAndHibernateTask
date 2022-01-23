package peaksoft.dao;

import peaksoft.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void createUsersTable();

    void dropUsersTable() throws SQLException;

    void saveUser(String name, String lastName, byte age) throws SQLException;

    void removeUserById(long id) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    void cleanUsersTable() throws SQLException;

}
