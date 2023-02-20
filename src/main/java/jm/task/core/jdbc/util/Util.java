package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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

    private static final String URL = "jdbc:mysql://localhost:3306/PP_1_1_3-4_JDBC_Hibernate";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static SessionFactory sessionFactory = null;
    private static Connection connection = null;

    public static Connection getConnectionJDBC() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                }
            } catch (SQLException e) {
                System.out.println("Не удалось подключиться к базе!");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static SessionFactory getConnectionHibernate() {

        if (sessionFactory == null || sessionFactory.isClosed()) {

            try {
                Configuration conf = new Configuration();
                Properties prop = new Properties();
                prop.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                prop.put(Environment.URL, URL);
                prop.put(Environment.USER, LOGIN);
                prop.put(Environment.PASS, PASSWORD);
                prop.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                prop.put(Environment.SHOW_SQL, "true");
                prop.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                prop.put(Environment.HBM2DDL_AUTO, "none");
                conf.setProperties(prop);
                conf.addAnnotatedClass(User.class);
                ServiceRegistry sR = new StandardServiceRegistryBuilder()
                        .applySettings(conf.getProperties()).build();
                sessionFactory = conf.buildSessionFactory(sR);

            } catch(HibernateException e) {
                    System.out.println("No connection!");
                    e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
