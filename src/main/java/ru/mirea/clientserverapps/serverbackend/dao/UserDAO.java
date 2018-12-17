package ru.mirea.clientserverapps.serverbackend.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.clientserverapps.serverbackend.mappers.UserMapper;
import ru.mirea.clientserverapps.serverbackend.models.User;


import javax.sql.DataSource;
import java.math.BigDecimal;

@Repository
@Transactional
public class UserDAO extends JdbcDaoSupport {

    @Autowired
    public UserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public void addUser(String name, BigDecimal balance, String Hash)
    {
        this.getJdbcTemplate().update("INSERT INTO users (Name, Balance, Hash) VALUES (?, ?, ?)",
                name, balance.toString(), Hash);
    }

    // get User by ID
    public User getUser(int id) {
        String sql = UserMapper.BASE_SQL + " where u.ID = ?";

        Object[] params = new Object[] { id };
        UserMapper mapper = new UserMapper();
        try {
            User user = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // get User by Name
    public User getUser(String name) {
        String sql = UserMapper.BASE_SQL + " where u.Name = ?";
        Object[] params = new Object[] { name };
        UserMapper mapper = new UserMapper();
        try {
            User user = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean doesUserExist(String name) {
        String sql = UserMapper.BASE_SQL + " where u.Name = ?";
        Object[] params = new Object[] { name };
        UserMapper mapper = new UserMapper();
        try {
            User user = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }



}
