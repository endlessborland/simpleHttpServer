package ru.mirea.clientserverapps.serverbackend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.clientserverapps.serverbackend.exceptions.IDNotFoundException;
import ru.mirea.clientserverapps.serverbackend.mappers.PetWrapperMapper;
import ru.mirea.clientserverapps.serverbackend.models.Pet;
import ru.mirea.clientserverapps.serverbackend.mappers.PetMapper;
import ru.mirea.clientserverapps.serverbackend.models.PetWrapper;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static ru.mirea.clientserverapps.serverbackend.mappers.PetWrapperMapper.BASE_SQL;

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

    public void buyPet(int id, int newCount)
    {
        String sql = "UPDATE pet SET Count = " + newCount + " WHERE ID = " + id;
        this.getJdbcTemplate().update(sql);
    }

    public List<PetWrapper> getPets()
    {
        String sql = PetWrapperMapper.BASE_SQL;
        PetWrapperMapper mapper = new PetWrapperMapper();
        try {
            List<PetWrapper> list = this.getJdbcTemplate().query(sql, mapper);
            return list;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
