package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS `pp_1_1_3-4_jdbc_hibernate`.`user` " +
                "(`id` INT NOT NULL AUTO_INCREMENT," +
                "`name` VARCHAR(45)," +
                "`lastName` VARCHAR(45)," +
                "`age` TINYINT(3)," +
                "PRIMARY KEY (id))";

        try (Connection connection = Util.getConnectionJDBC();
                 Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS `pp_1_1_3-4_jdbc_hibernate`.`user`";

        try (Connection connection = Util.getConnectionJDBC();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO `pp_1_1_3-4_jdbc_hibernate`.`user` (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection connection = Util.getConnectionJDBC();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        String sql = "DELETE FROM `pp_1_1_3-4_jdbc_hibernate`.`user` WHERE `id` = ?";

        try (Connection connection = Util.getConnectionJDBC();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("id: %s - не существует.\n", id);
        }
    }

    public List<User> getAllUsers() {

        String sql = "SELECT `id`, `name`, `lastName`, `age` FROM `pp_1_1_3-4_jdbc_hibernate`.`user`";
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getConnectionJDBC();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {

        String sql = "TRUNCATE `pp_1_1_3-4_jdbc_hibernate`.`user`";

        try (Connection connection = Util.getConnectionJDBC();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
