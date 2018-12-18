package ru.mirea.clientserverapps.serverbackend.mappers;

import ru.mirea.clientserverapps.serverbackend.ItemType;
import ru.mirea.clientserverapps.serverbackend.models.Pet;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetMapper implements RowMapper<Pet> {
    public static final String BASE_SQL = "Select p.ID, p.Count, p.Type, p.Info, p.AInfo, p.Price from pet p";

    @Override
    public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Pet(rs.getInt("ID"), rs.getInt("Count"), rs.getString("Type"), rs.getString("Info"),
                rs.getString("AInfo"), rs.getString("Price"), ItemType.TypePet);
    }
}
