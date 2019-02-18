package com.branthragan.vending_machine.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class InventoryRepository {
    private static final String QUERY_INVENTORY =
            "SELECT id, name, total FROM inventory";

    private static final String QUERY_INVENTORY_ITEM =
            "SELECT id, name, total FROM inventory WHERE id = :id";

    private static final String UPDATE_ITEM_TOTAL =
            "UPDATE inventory SET total = :total WHERE id = :id";

    private static final String ADD_NEW_ITEM =
            "INSERT INTO inventory (id, name, total) VALUES (:id, :name, :total)";

    private DataSource dataSource;
    private NamedParameterJdbcTemplate template;

    @Autowired
    public InventoryRepository(DataSource dataSource) {
        this.dataSource = dataSource;

        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<InventoryItem> getInventory() {
        return template.query(QUERY_INVENTORY, (resultSet, i) -> new InventoryItem(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("total")));
    }

    public InventoryItem getInventoryItem(Long id) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        return template.queryForObject(QUERY_INVENTORY_ITEM, params, InventoryItem.class);
    }

    public InventoryItem updateItemTotal(InventoryItem item) {
        InventoryItem dbItem = getInventoryItem(item.getId());

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("total", item.getCount())
                .addValue("id", item.getId());

        if (null != dbItem) {
            template.update(UPDATE_ITEM_TOTAL, params);
        }

        return getInventoryItem(item.getId());
    }

    //TODO Future implementation for restocking machine
    public List<InventoryItem> addItem(InventoryItem item) {
        //TODO add duplicate protection

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", item.getId())
                .addValue("name", item.getName())
                .addValue("total", item.getCount());

        template.update(ADD_NEW_ITEM, params);

        return getInventory();
    }
}
