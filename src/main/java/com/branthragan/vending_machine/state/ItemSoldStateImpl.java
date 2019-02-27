package com.branthragan.vending_machine.state;

import com.branthragan.vending_machine.inventory.InventoryItem;
import com.branthragan.vending_machine.log.TransactionLog;
import com.branthragan.vending_machine.machine.VendingMachine;


public class ItemSoldStateImpl implements VendingState {
    private static final int DISPENSE_DELTA = -1;
    private static final String ITEM_SOLD = "Item Sold";

    private TransactionLog log;
    private VendingStateManager stateManager;

    ItemSoldStateImpl(TransactionLog log, VendingStateManager stateManager) {
        this.log = log;
        this.stateManager = stateManager;
    }

    @Override
    public String insertFunds(VendingMachine machine) {
        String message = ITEM_SOLD + " " + INVALID_ACTION;
        log.logInteraction(message);

        machine.setState(this);

        return message;
    }

    @Override
    public String ejectFunds(VendingMachine machine) {
        String message = ITEM_SOLD + " " + INVALID_ACTION;
        log.logInteraction(message);

        machine.setState(this);

        return message;
    }

    @Override
    public void selectItem(VendingMachine machine, InventoryItem item) {
        log.logInteraction(ITEM_SOLD + " " + INVALID_ACTION);

        machine.setState(this);
    }

    @Override
    public String dispense(VendingMachine machine, InventoryItem item) {
        String message;

        if (machine.hasItemInInventory(item.getId())) {
            machine.releaseItem(item);
            machine.updateInventory(item, DISPENSE_DELTA);
            message = String.format("Enjoy your %s! %d remaining.", item.getName(), item.getCount());

            if (machine.hasItemsInInventory()) {
                machine.setState(stateManager.getNoFundsState());
            } else {
                machine.setState(stateManager.getSoldOutState());
            }
        } else {
            message = String.format("%s is sold out. Please select another.", item.getName());
        }

        return message;
    }

    @Override
    public String toString() {
        return ITEM_SOLD;
    }
}
