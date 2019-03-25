package com.gavrilov.db;

import com.gavrilov.common.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public final class ConfigurationDb {
    private static final Logger log = LoggerFactory.getLogger(ConfigurationDb.class);

    public static Connection getConnection() {
        Map<String, String> propertiesConnection = PropertiesManager.getPropertiesConnection("db.properties");
        final String url = propertiesConnection.get("url");
        final String password = propertiesConnection.get("password");
        final String user = propertiesConnection.get("user");
        final String driver = propertiesConnection.get("driver");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.error("Ошибка при загрузке драйвера бд", e);
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            log.error("Ошибка при попытке подключиться к бд", e);
        }

        return connection;
    }


    public static void closeConnection(@NotNull Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Ошибка при закрытии подключения к бд", e);
        }
    }
}
