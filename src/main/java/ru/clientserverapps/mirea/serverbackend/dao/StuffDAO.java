package ru.clientserverapps.mirea.serverbackend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;

public class StuffDAO extends JdbcDaoSupport {

    @Autowired
    public StuffDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
}
