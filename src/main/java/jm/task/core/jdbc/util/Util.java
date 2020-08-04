package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Util {
    private String userName = "root";
    private String password = "12345iB";
    private String connectionUrl = "jdbc:mysql://localhost:3306/lesson113?serverTimezone=Europe/Moscow&useSSL=false";
    Connection connection = null;

    public Connection setConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            System.out.println("We're connected");
        } catch (SQLException e) {
            System.out.println("Didn't connected (class Util)");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        String path = "hibernate.cfg.xml";
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure(path);
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
    public static void shutdown() {
        getSessionFactory().close();
    }
}
