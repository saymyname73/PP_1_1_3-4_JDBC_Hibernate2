package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String dbURL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String dbUserName = "root";
    private static final String dbPassword = "root";

    private static Connection connection;

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
        } catch (SQLException e) {
            System.out.println("Не удалось загрузить класс драйвера");
        }

        return connection;
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            Properties setting = new Properties();
            setting.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            setting.put(Environment.URL, "jdbc:mysql://localhost:3306/mydbtest?useSSL=false");
            setting.put(Environment.USER, "root");
            setting.put(Environment.PASS, "root");
            setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            setting.put(Environment.SHOW_SQL, "true");
            setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            configuration.setProperties(setting);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
