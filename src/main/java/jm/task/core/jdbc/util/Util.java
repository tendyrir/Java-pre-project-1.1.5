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
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String LOCALHOST_DB_URL = "jdbc:mysql://localhost:3306/kata";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String MYSQL5_DIALECT = "org.hibernate.dialect.MySQL5Dialect";

    private static Connection connection;
    private static SessionFactory sessionFactory;

    public static Connection connectToMySqlDatabaseJDBC() {
        try {
            Class.forName(MYSQL_DRIVER);
            connection = DriverManager.getConnection(LOCALHOST_DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory connectToMySqlDatabaseHibernate() {
        try {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, MYSQL_DRIVER);
            settings.put(Environment.URL, LOCALHOST_DB_URL);
            settings.put(Environment.USER, USERNAME);
            settings.put(Environment.PASS, PASSWORD);
            settings.put(Environment.DIALECT, MYSQL5_DIALECT);
            settings.put(Environment.SHOW_SQL, "false");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            configuration.setProperties(settings);
            configuration.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}

