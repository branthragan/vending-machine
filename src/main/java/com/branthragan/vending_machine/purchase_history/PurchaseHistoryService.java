package com.branthragan.vending_machine.purchase_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PurchaseHistoryService {
    private PurchaseHistoryRepository repository;

    @Autowired
    public PurchaseHistoryService(PurchaseHistoryRepository repository) {
        this.repository = repository;
    }

    public void insertPurchaseHistory(Long id) {
        Date currentDate = new Date();
        repository.insertPurchaseHistory(id, currentDate);
    }
}
