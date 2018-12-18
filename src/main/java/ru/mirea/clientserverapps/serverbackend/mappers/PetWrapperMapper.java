package ru.mirea.clientserverapps.serverbackend.mappers;

import ru.mirea.clientserverapps.serverbackend.ItemType;
import ru.mirea.clientserverapps.serverbackend.models.Pet;
import ru.mirea.clientserverapps.serverbackend.models.PetWrapper;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PetWrapperMapper implements RowMapper<PetWrapper> {
    public static final String BASE_SQL = "Select p.ID, p.Type, p.Info, p.Price from pet p";

    @Override
    public PetWrapper mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PetWrapper(rs.getInt("ID"), rs.getString("Type"), rs.getString("Info"),
                rs.getString("Price"));
    }

}
