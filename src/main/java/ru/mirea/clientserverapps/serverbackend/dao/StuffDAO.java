package ru.mirea.clientserverapps.serverbackend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository
@Transactional
public class StuffDAO extends JdbcDaoSupport {

    @Autowired
    public StuffDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
}
