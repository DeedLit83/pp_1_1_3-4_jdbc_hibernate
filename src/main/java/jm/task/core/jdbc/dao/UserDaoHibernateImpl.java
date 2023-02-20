package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `pp_1_1_3-4_jdbc_hibernate`.`user` " +
                "(`id` INT NOT NULL AUTO_INCREMENT," +
                "`name` VARCHAR(255)," +
                "`lastName` VARCHAR(255)," +
                "`age` TINYINT(3)," +
                "PRIMARY KEY (id))";
        Session session = Util.getConnectionHibernate().openSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery(sql)
                .executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS `pp_1_1_3-4_jdbc_hibernate`.`user`";
        Session session = Util.getConnectionHibernate().openSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery(sql)
                .executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getConnectionHibernate().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        String sql = "delete from user where id = :id";
        Session session = Util.getConnectionHibernate().openSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery(sql)
                .setParameter("id", id)
                .executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>)  Util.getConnectionHibernate().openSession().createQuery("From User").list();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE `pp_1_1_3-4_jdbc_hibernate`.`user`";
        Session session = Util.getConnectionHibernate().openSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery(sql)
                .executeUpdate();
        transaction.commit();
        session.close();
    }
}
