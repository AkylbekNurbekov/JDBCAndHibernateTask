package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users(" +
                    "id serial not null," +
                    "name VARCHAR(50) NOT NULL," +
                    "lastName VARCHAR(50) NOT NULL," +
                    "age int not null" +
                    ");";
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Таблица создана!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Таблица не создана!");
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("drop table users").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Таблица удалена!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Таблица не удалена!");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            session.close();
            System.out.println("'" + name + " " + lastName + "' добавлен БД!");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.err.println("'" + name + " " + lastName + "' не добавлены БД!");

        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            session.close();
            System.out.println("Человек с ID '" + id + "' удален!");
        } catch (HibernateException e) {
            e.getMessage();
            System.out.println("Человек с ID '" + id + "' не удален!");

        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            List getUser = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            session.close();
            System.out.println("Удалось вывести из БД!\n" +
                    "------------------------------------------------------");
            return getUser;
        } catch (HibernateException e) {
            e.getMessage();
            System.out.println("Не удалось вывести из БД!");
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Таблица  очищена!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Таблица не очищена!");
        }
    }
}
