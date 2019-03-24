package com.gavrilov.mapper;

import com.gavrilov.model.Url;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UrlMapper implements RowMapper<Url> {
    private static final Logger log = LoggerFactory.getLogger(UrlMapper.class);

    @Override
    public Url mapRow(@NotNull ResultSet resultSet) {
        Url url = new Url();
        try {
            if (resultSet.next()) {
                url.setId(resultSet.getLong(1));
                url.setDescription(resultSet.getString(2));
                url.setValue(resultSet.getString(3));
            }
        } catch (SQLException e) {
            log.error("Ошибка при маппинге результата запроса в сущность Url");
        }
        return url;
    }

    @Override
    public List<Url> mapRows(@NotNull ResultSet resultSet) {
        List<Url> urls = new LinkedList<>();
        try {
            while (resultSet.next()) {
                Url url = new Url();
                url.setId(resultSet.getLong(1));
                url.setDescription(resultSet.getString(2));
                url.setValue(resultSet.getString(3));
                urls.add(url);
            }
        } catch (SQLException e) {
            log.error("Ошибка при маппинге результата запроса в сущность Url");
        }
        return urls;
    }
}
