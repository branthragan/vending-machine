package com.branthragan.vending_machine.machine;

import com.branthragan.vending_machine.inventory.InventoryItem;
import com.branthragan.vending_machine.log.TransactionLog;
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

    private VendingStateManager stateManager;
    private VendingService service;

    private VendingState state;

    private Map<Long, InventoryItem> inventory;

    @Autowired
    public VendingMachine(VendingStateManager stateManager,
                          VendingService service,
                          TransactionLog log) {

        this.stateManager = stateManager;
        this.service = service;
        this.log = log;

        this.initializeInventory();

        if (this.hasItemsInInventory()) {
            this.setState(stateManager.getNoFundsState());
        } else {
            this.setState(stateManager.getSoldOutState());
        }
    }

    public void initializeInventory() {
        List<InventoryItem> items = service.getInventory();

        this.inventory = new HashMap<>();
        items.forEach(item -> this.inventory.put(item.getId(), item));
    }

    public void insertFunds() {
        this.state.insertFunds(this);
    }

    public void ejectFunds() {
        this.state.ejectFunds(this);
    }

    public void selectItem(Long id) {
        if (this.hasItemInInventory(id)) {
            this.state.selectItem(this, this.inventory.get(id));
            this.state.dispense(this, this.inventory.get(id));
        } else {
            this.log.logInteraction(
                    String.format("%s is sold out. Please select another.", this.inventory.get(id).getName()));

            this.setState(stateManager.getHasFundsState());
        }
    }

    public void releaseItem(InventoryItem item) {
        log.logPurchase(String.format("Release %s", item.getName()));
    }

    public void updateInventory(InventoryItem item, int delta) {
        if (this.inventory.containsKey(item.getId())) {
            InventoryItem currentItem = this.inventory.get(item.getId());
            if (currentItem.getCount() > 0) {
                currentItem.updateCount(delta);
                service.updateItemTotal(currentItem);
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

    public VendingState getState() {
        return state;
    }

    public Map<Long, InventoryItem> getInventory() {
        return inventory;
    }
}
