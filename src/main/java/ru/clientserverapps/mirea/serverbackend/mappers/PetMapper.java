package ru.clientserverapps.mirea.serverbackend.mappers;

import ru.clientserverapps.mirea.serverbackend.models.Pet;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PetMapper implements RowMapper<Pet> {
    public static final String BASE_SQL = "Select p.ID, p.Count, p.Type, p.Info, p.AInfo, p.Price from Pet p";

    @Override
    public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Pet(rs.getInt("ID"), rs.getInt("Count"), rs.getString("Type"), rs.getString("Info"),
                rs.getString("AInfo"), rs.getString("Price"));
    }
}
