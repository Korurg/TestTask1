package com.korurg.testtask0.nonreactive.configuration;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        //TODO: как-то вытащить из кода логин, пароль и т.п.
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/postgres")
                .username("postgres")
                .password("123")
                .build();
    }

}
