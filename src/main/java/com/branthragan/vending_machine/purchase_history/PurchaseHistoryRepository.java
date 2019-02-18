package com.branthragan.vending_machine.purchase_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;

@Repository
public class PurchaseHistoryRepository {
    private static final String INSERT_PURCHASE_HISTORY =
            "INSERT INTO purchase_history (transaction_datetime, id) VALUES (:transactionDatetime, :id)";

    private NamedParameterJdbcTemplate template;

    @Autowired
    public PurchaseHistoryRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    public void insertPurchaseHistory(Long id, Date currentDate) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("transactionDatetime", currentDate)
                .addValue("id", id);

        template.update(INSERT_PURCHASE_HISTORY, params);
    }
}
