package ru.mirea.clientserverapps.serverbackend.mappers;

import ru.mirea.clientserverapps.serverbackend.enums.ItemType;
import ru.mirea.clientserverapps.serverbackend.models.Pet;

import org.springframework.jdbc.core.RowMapper;
import ru.mirea.clientserverapps.serverbackend.models.Stuff;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StuffMapper implements RowMapper<Stuff>{
    public static final String BASE_SQL = "Select p.ID, p.Count, p.Price, p.Name, p.Description from stuff p";

    @Override
    public Stuff mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Stuff(rs.getInt("ID"), rs.getString("Name"), rs.getString("Description"), rs.getString("Price"),
                rs.getInt("Count"));
    }
}
