package ru.mirea.clientserverapps.serverbackend.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.mirea.clientserverapps.serverbackend.models.ProductTrayWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTrayWrapperMapper implements RowMapper<ProductTrayWrapper> {

    public static final String BASE_SQL = "Select ID, ProductID, Count, ItemType from ";

    @Override
    public ProductTrayWrapper mapRow(ResultSet rs, int i) throws SQLException {
        return new ProductTrayWrapper(rs.getInt("ID"), rs.getInt("ProductID"),
                rs.getInt("Count"), rs.getString("ItemType"));
    }
}
