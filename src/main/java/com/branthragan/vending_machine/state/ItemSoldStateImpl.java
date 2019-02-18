package com.branthragan.vending_machine.state;

import com.branthragan.vending_machine.log.TransactionLog;
import com.branthragan.vending_machine.machine.VendingMachine;
import sun.plugin.dom.exception.InvalidStateException;


public class ItemSoldStateImpl implements VendingState {
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
    public void selectItem(VendingMachine machine, String item) {
        log.logInteraction(INVALID_ACTION);

        machine.setState(this);
    }

    @Override
    public void dispense(VendingMachine machine, String item) {
        String message = String.format("Dispense %s", item);
        log.logPurchase(message);

        if (machine.hasItemInInventory(item)) {
            machine.updateInventory(item, -1);

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
}
