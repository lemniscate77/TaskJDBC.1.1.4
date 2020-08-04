package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao, Serializable {
    private String tableName = "users";
    Util util = new Util();
    Connection connection = null;
    Statement statement = null;
    public final SessionFactory sessionFactory = util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        String createUsersTable = "CREATE TABLE IF NOT EXISTS " +
                tableName +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY , " +
                "name VARCHAR(30) NOT NULL, " +
                "lastName VARCHAR(30) NOT NULL, " +
                "age MEDIUMINT NOT NULL)";
        session.createSQLQuery(createUsersTable).executeUpdate();
        session.getTransaction().commit();
        System.out.println("Таблица " + tableName+" создана ");
        session.close();
        //sessionFactory.close();

    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String dropUsersTable = "DROP TABLE IF EXISTS " +
                tableName;
        session.createSQLQuery(dropUsersTable).executeUpdate();
        session.getTransaction().commit();
        System.out.println("Таблица " + tableName+" УДАЛЕНА! ");
        session.close();
        //sessionFactory.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        createUsersTable();
        User user1 = new User(name,lastName,age);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(user1);
        transaction.commit();
        System.out.println("user " + name + " добавлен");
        session.close();
        //sessionFactory.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        System.out.println("User с id = " + id + " удален");
        transaction.commit();
        session.close();
        //sessionFactory.close();

    }

    @Override
    public List<User> getAllUsers() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM users").list();
        transaction.commit();
        session.close();
        //sessionFactory.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        String users = "users";
        Transaction t = session.beginTransaction();
        String hql = String.format("DELETE FROM %s", users);
        Query query = session.createQuery(hql);
        query.executeUpdate();
        System.out.println("Таблица 'users' \u2014 очищена");
        t.commit();
        session.close();
        //sessionFactory.close();

    }
}
