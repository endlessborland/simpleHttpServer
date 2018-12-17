package ru.mirea.clientserverapps.serverbackend.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.mirea.clientserverapps.serverbackend.models.Session;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionMapper implements RowMapper {
    public static final String BASE_SQL = "Select s.ID, s.UserID, s.AToken, s.RToken from sessions s";

    @Override
    public Session mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Session(rs.getInt("ID"), rs.getInt("UserID"), rs.getString("AToken"),
                rs.getString("RToken"));
    }
}
