package ru.mirea.clientserverapps.serverbackend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.clientserverapps.serverbackend.models.Pet;
import ru.mirea.clientserverapps.serverbackend.mappers.PetMapper;

import javax.sql.DataSource;

@Repository
@Transactional
public class PetDAO extends JdbcDaoSupport {

    @Autowired
    public PetDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Pet getPet(int id) {
        String sql = PetMapper.BASE_SQL + " where p.id = ?";

        Object[] params = new Object[] { id };
        PetMapper mapper = new PetMapper();
        try {
            Pet pet = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return pet;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
