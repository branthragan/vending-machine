package com.branthragan.vending_machine.state;

import com.branthragan.vending_machine.inventory.InventoryItem;
import com.branthragan.vending_machine.log.TransactionLog;
import com.branthragan.vending_machine.machine.VendingMachine;
import sun.plugin.dom.exception.InvalidStateException;


public class ItemSoldStateImpl implements VendingState {
    private static final int DISPENSE_DELTA = -1;

    private TransactionLog log;
    private VendingStateManager stateManager;

    ItemSoldStateImpl(TransactionLog log, VendingStateManager stateManager) {
        this.log = log;
        this.stateManager = stateManager;
    }

    @Override
    public void insertFunds(VendingMachine machine) {
        log.logInteraction(INVALID_ACTION);

        machine.setState(this);
    }

    @Override
    public void ejectFunds(VendingMachine machine) {
        log.logInteraction(INVALID_ACTION);

        machine.setState(this);
    }

    @Override
    public void selectItem(VendingMachine machine, InventoryItem item) {
        log.logInteraction(INVALID_ACTION);

        machine.setState(this);
    }

    @Override
    public void dispense(VendingMachine machine, InventoryItem item) {
        if (machine.hasItemInInventory(item.getId())) {
            machine.releaseItem(item);
            machine.updateInventory(item, DISPENSE_DELTA);

            if (machine.hasItemsInInventory()) {
                machine.setState(stateManager.getNoFundsState());
            } else {
                machine.setState(stateManager.getSoldOutState());
            }
        } else {
            String inventoryMismatch =
                    String.format("Inventory mismatch. Unable to dispense %s Please make another selection", item);
            log.logError(inventoryMismatch);
            throw new InvalidStateException(inventoryMismatch);
        }
    }

    @Override
    public String toString() {
        return "Item Sold";
    }
}
