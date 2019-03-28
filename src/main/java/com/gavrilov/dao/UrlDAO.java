package com.gavrilov.dao;

import com.gavrilov.db.ConfigurationDb;
import com.gavrilov.mapper.UrlMapper;
import com.gavrilov.model.Url;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UrlDAO implements DAO<Url> {
    private static final Logger log = LoggerFactory.getLogger(UrlDAO.class);

    //region Можно заместо ConfigurationDb и profiles в maven использовать Resource tomcat и данную аннатацию
/*    @Resource(name = "jdbc/TestDB")
    private DataSource dataSource;*/
    //endregion

    @Override
    public Url findById(@NotNull Long id) {
        Connection connection = ConfigurationDb.getConnection();
        final String sql = "Select * from url where id = ?";
        Url url = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            url = new UrlMapper().mapRow(resultSet);
            log.debug("Запрос " + sql + " выполнен успещно");
        } catch (SQLException e) {
            log.error("Ошибка при выполнениии запроса " + sql);
        } finally {
            ConfigurationDb.closeConnection(connection);
        }
        return url;
    }

    @Override
    public List<Url> findAll() {
        Connection connection = ConfigurationDb.getConnection();
        final String sql = "Select * from url";
        List<Url> urls = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            urls = new UrlMapper().mapRows(resultSet);
            log.debug("Запрос " + sql + " выполнен успещно");
        } catch (SQLException e) {
            log.error("Ошибка при выполнениии запроса " + sql);
        } finally {
            ConfigurationDb.closeConnection(connection);
        }
        return urls;
    }

    @Override
    public void create(@NotNull Url entity) {

    }

    @Override
    public Url update(@NotNull Url entity) {
        return null;
    }

    @Override
    public void deleteById(@NotNull Long id) {

    }
}
