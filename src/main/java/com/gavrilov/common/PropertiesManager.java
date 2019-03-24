package com.gavrilov.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class PropertiesManager {
    private static final Logger log = LoggerFactory.getLogger(PropertiesManager.class);

    public static Map<String, String> getPropertiesConnection(String propertiesName) {
        Map<String, String> map = new HashMap<>();
        Properties prop = new Properties();
        InputStream resourceAsStream = PropertiesManager.class.getClassLoader().getResourceAsStream(propertiesName);
        try {
            prop.load(resourceAsStream);
            map.put("url", prop.getProperty("db.url"));
            map.put("driver", prop.getProperty("db.driver"));
            map.put("user", prop.getProperty("db.user"));
            map.put("password", prop.getProperty("db.password"));
        } catch (IOException e) {
            log.error("Ошибка при загрузке свойств бд", e);
        }
        return map;
    }
}
