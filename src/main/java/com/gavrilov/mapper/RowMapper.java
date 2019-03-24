package com.gavrilov.mapper;

import java.sql.ResultSet;
import java.util.List;

public interface RowMapper<T> {
    T mapRow(ResultSet resultSet);

    List<T> mapRows(ResultSet resultSet);
}
