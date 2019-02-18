package com.branthragan.vending_machine.machine;

import com.branthragan.vending_machine.demo.DemoInventory;
import com.branthragan.vending_machine.log.TransactionLog;
import com.branthragan.vending_machine.state.VendingState;
import com.branthragan.vending_machine.state.VendingStateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Scope("singleton")
public class VendingMachine {
    private TransactionLog log;

    private VendingStateManager stateManager;

    private VendingState state;

    private Map<String, Integer> inventory;

    @Autowired
    public VendingMachine(VendingStateManager stateManager,
                          TransactionLog log) {

        this.stateManager = stateManager;
        this.log = log;

        this.setState(stateManager.getSoldOutState());

        //TODO Move this to a callable location
        this.initializeInventory(DemoInventory.buildInventory());
    }

    public void initializeInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;

        if (this.hasItemsInInventory()) {
            this.setState(stateManager.getNoFundsState());
        } else {
            this.setState(stateManager.getSoldOutState());
        }
    }

    public void insertFunds() {
        this.state.insertFunds(this);
    }

    public void ejectFunds() {
        this.state.ejectFunds(this);
    }

    public void selectItem(String item) {
        if (this.hasItemInInventory(item)) {
            this.state.selectItem(this, item);
            this.state.dispense(this, item);
        } else {
            String message = String.format("%s is sold out. Please select another.", item);
            this.log.logInteraction(message);

            this.setState(stateManager.getHasFundsState());
        }
    }

    public void updateInventory(String item, int netChange) {
        int count;
        if (this.inventory.containsKey(item)) {
            if (this.inventory.get(item) > 0) {
                count = this.inventory.get(item);
                count = count + netChange;
                this.inventory.put(item, count);
            } else {
                String message = "Inventory mismatch. Unable to update";
                this.log.logError(message);
                throw new InvalidStateException(message);
            }
        } else if (netChange > 0) {
            this.inventory.put(item, netChange);
        }
    }

    public int getCount() {
        AtomicInteger total = new AtomicInteger();
        inventory.forEach((item, count) -> total.addAndGet(count));
        return total.intValue();
    }

    public boolean hasItemsInInventory() {
        return inventory.entrySet().stream().anyMatch(item -> item.getValue() > 0);
    }

    public boolean hasItemInInventory(String item) {
        return inventory.containsKey(item) && inventory.get(item) > 0;
    }

    public void setState(VendingState state) {
        this.state = state;
    }

    public VendingState getState() {
        return state;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public TransactionLog getLog() {
        return log;
    }
}
