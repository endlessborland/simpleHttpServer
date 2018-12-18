package ru.mirea.clientserverapps.serverbackend.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.clientserverapps.serverbackend.mappers.ProductTrayWrapperMapper;
import ru.mirea.clientserverapps.serverbackend.models.ProductTrayWrapper;
import ru.mirea.clientserverapps.serverbackend.models.User;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class TrayDAO extends JdbcDaoSupport {

    public TrayDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public void createTray(User user) {
        this.getJdbcTemplate().execute("CREATE TABLE " + user.getTray() + " " +
                "(ID int PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                "ProductID int NOT NULL, " +
                "Count int NOT NULL, " +
                "ItemType varchar(255) NOT NULL);");
    }

    public void emptyTray(User user) {
        this.getJdbcTemplate().execute("TRUNCATE table " + user.getTray());
    }

    public List<ProductTrayWrapper> getTrayItems(User user) {
        String sql = ProductTrayWrapperMapper.BASE_SQL + user.getTray();
        ProductTrayWrapperMapper mapper = new ProductTrayWrapperMapper();
        try {
            List<ProductTrayWrapper> list = this.getJdbcTemplate().query(sql, mapper);
            return list;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void addToCart(User user, ProductTrayWrapper productTrayWrapper)
    {
        this.getJdbcTemplate().update("INSERT INTO " + user.getTray() + " (ProductID, Count, ItemType) " +
                "VALUES (?, ?, ?)", productTrayWrapper.getProductID(), productTrayWrapper.getCount(),
                productTrayWrapper.getItemType());
    }

    public void removeFromCart(User user, int id)
    {
        String sql = "DELETE FROM " + user.getTray() + " WHERE ID=" + id;
        this.getJdbcTemplate().update(sql);
    }
}
