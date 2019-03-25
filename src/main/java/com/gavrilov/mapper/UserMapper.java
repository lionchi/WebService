package com.gavrilov.mapper;

import com.gavrilov.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class UserMapper implements RowMapper<User> {

    private static final Logger log = LoggerFactory.getLogger(UserMapper.class);

    @Override
    public User mapRow(@NotNull ResultSet resultSet) {
        User resultUser = null;
        try {
            Map<Long, User> userMap = new HashMap<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                Integer enable = resultSet.getInt("enable");
                String roleName = resultSet.getString("name");
                User user = userMap.computeIfAbsent(id, aLong -> {
                    User createUser = new User();
                    createUser.setId(id);
                    createUser.setLogin(login);
                    createUser.setPassword(password);
                    createUser.setEnable(enable);
                    return createUser;
                });
                user.getRoleNames().add(roleName);
            }
            Iterator<User> iterator = userMap.values().iterator();
            if (iterator.hasNext()) {
                resultUser = iterator.next();
            }

        } catch (SQLException e) {
            log.error("Ошибка при маппинге результата запроса в сущность Url");
        }
        return resultUser;
    }

    @Override
    public List<User> mapRows(@NotNull ResultSet resultSet) {
        return null;
    }
}
