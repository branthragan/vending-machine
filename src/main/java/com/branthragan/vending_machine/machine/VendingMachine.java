package com.branthragan.vending_machine.machine;

import com.branthragan.vending_machine.inventory.InventoryItem;
import com.branthragan.vending_machine.log.TransactionLog;
import com.branthragan.vending_machine.purchase_history.PurchaseHistoryService;
import com.branthragan.vending_machine.state.VendingState;
import com.branthragan.vending_machine.state.VendingStateManager;
import com.branthragan.vending_machine.vending_service.VendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Scope("singleton")
public class VendingMachine {
    private TransactionLog log;

    private VendingService vendingService;
    private PurchaseHistoryService historyService;

    private VendingState state;

    private Map<Long, InventoryItem> inventory;

    @Autowired
    public VendingMachine(VendingStateManager stateManager,
                          VendingService vendingService,
                          PurchaseHistoryService historyService,
                          TransactionLog log) {

        this.vendingService = vendingService;
        this.historyService = historyService;
        this.log = log;

        this.initializeInventory();

        if (this.hasItemsInInventory()) {
            this.setState(stateManager.getNoFundsState());
        } else {
            this.setState(stateManager.getSoldOutState());
        }
    }

    private void initializeInventory() {
        List<InventoryItem> items = vendingService.getInventory();

        this.inventory = new HashMap<>();
        items.forEach(item -> this.inventory.put(item.getId(), item));
    }

    public String insertFunds() {
        return this.state.insertFunds(this);
    }

    public String ejectFunds() {
        return this.state.ejectFunds(this);
    }

    public String selectItem(Long id) {
        String message;
        InventoryItem requestedItem = this.inventory.get(id);

        this.state.selectItem(this, requestedItem);
        message = this.state.dispense(this, requestedItem);

        return message;
    }

    public void releaseItem(InventoryItem item) {
        log.logPurchase(String.format("Release %s", item.getName()));
        historyService.insertPurchaseHistory(item.getId());
    }

    public void updateInventory(InventoryItem item, int delta) {
        if (this.inventory.containsKey(item.getId())) {
            InventoryItem currentItem = this.inventory.get(item.getId());
            if (currentItem.getCount() > 0) {
                currentItem.updateCount(delta);
                vendingService.updateItemTotal(currentItem);
            } else {
                String message = "Inventory mismatch. Unable to update";
                this.log.logError(message);
                throw new InvalidStateException(message);
            }
        }
    }

    public int getCount() {
        AtomicInteger total = new AtomicInteger();
        inventory.forEach(((id, item) -> total.addAndGet(item.getCount())));
        return total.intValue();
    }

    public boolean hasItemsInInventory() {
        return inventory.entrySet().stream().anyMatch(entry -> entry.getValue().getCount() > 0);
    }

    public boolean hasItemInInventory(Long id) {
        return inventory.containsKey(id) && inventory.get(id).getCount() > 0;
    }

    public void setState(VendingState state) {
        this.state = state;
    }

    public Map<Long, InventoryItem> getInventory() {
        return inventory;
    }
}
