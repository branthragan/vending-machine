package com.branthragan.vending_machine.vending_service;

import com.branthragan.vending_machine.inventory.InventoryItem;
import com.branthragan.vending_machine.inventory.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendingService {
    private InventoryRepository inventory;

    @Autowired
    public VendingService(InventoryRepository inventory) {
        this.inventory = inventory;
    }

    public List<InventoryItem> getInventory() {
        return inventory.getInventory();
    }

    public InventoryItem updateItemTotal(InventoryItem item) {
        return inventory.updateItemTotal(item);
    }

    public List<InventoryItem> addNewItem(InventoryItem item) {
        return inventory.addItem(item);
    }
}
