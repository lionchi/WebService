package com.gavrilov.dao;

import com.gavrilov.common.PasswordManager;
import com.gavrilov.db.ConfigurationDb;
import com.gavrilov.mapper.UserMapper;
import com.gavrilov.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements DAO<User> {
    private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

    public User findByLoginAndPassword(final String login, final String password) {
        Connection connection = ConfigurationDb.getConnection();
        final String sql = "select user.id, user.login, user.password, user.enable, r.name from user " +
                "left join role r on user.id = r.user_id where user.login = ? and user.password = ?";
        User findUser = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, PasswordManager.MD5.crypt(password));
            ResultSet resultSet = statement.executeQuery();
            findUser = new UserMapper().mapRow(resultSet);
            log.debug("Запрос " + sql + " выполнен успещно");
        } catch (SQLException e) {
            log.error("Ошибка при выполнениии запроса " + sql);
        } finally {
            ConfigurationDb.closeConnection(connection);
        }
        return findUser;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void create(User entity) {

    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
