package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import static peaksoft.util.Util.connection;


public class UserDaoJdbcImpl implements UserDao {
//List<User>list = new ArrayList<>();

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        try {
            PreparedStatement statement = connection().prepareStatement(
                    "CREATE TABLE userTable (" +
                            "id  serial primary key not null," +
                            "name VARCHAR(300)NOT NULL," +
                            "lastName VARCHAR(300)NOT NULL," +
                            "age INT NOT NULL);");
            statement.execute();
            System.out.println("Таблица создана!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void dropUsersTable() {
        try {
            PreparedStatement statement = connection().prepareStatement("drop table userTable;");
            statement.execute();
            System.out.println("Таблица удалено!");
        } catch (SQLException e) {
            System.out.println("Таблица не удалено!");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = connection().prepareStatement("INSERT INTO userTable(name,lastName,age) values (?,?,?);");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setLong(3, age);
            preparedStatement.executeUpdate();
            System.out.println(name + " добавлен в базу!!!");
        } catch (SQLException e) {
            System.out.println();
            e.getMessage();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement statement = connection().prepareStatement("delete from userTable where id = ?");
            statement.setLong(1, id);
            statement.execute();
            System.out.println("пользователь с id-'" + id + "' удален!");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers()  {
        List<User>list = new ArrayList<>();

//        list.clear();
        try {
            PreparedStatement preparedStatement =
                    connection().prepareStatement("select * from userTable ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                Long id = (resultSet.getLong(1));
                String name = (resultSet.getString(2));
                String lastName = (resultSet.getString(3));
                Byte age = (resultSet.getByte(4));
                user.setId(id);
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);
                list.add(user);
            }
            for (User u:list) {
                System.out.println(u);

            }
            preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("not table");
        }
        return list;

    }

    public void cleanUsersTable() {
        try {
            PreparedStatement statement = connection().prepareStatement("delete from userTable");
            statement.execute();
            System.out.println("таблица очищена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
