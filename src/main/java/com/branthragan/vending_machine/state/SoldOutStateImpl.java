package com.branthragan.vending_machine.state;

import com.branthragan.vending_machine.inventory.InventoryItem;
import com.branthragan.vending_machine.log.TransactionLog;
import com.branthragan.vending_machine.machine.VendingMachine;


public class SoldOutStateImpl implements VendingState {
    private TransactionLog log;

    SoldOutStateImpl(TransactionLog log) {
        this.log = log;
    }

    @Override
    public String insertFunds(VendingMachine machine) {
        log.logInteraction(SOLD_OUT);

        machine.setState(this);

        return SOLD_OUT;
    }

    @Override
    public String ejectFunds(VendingMachine machine) {
        log.logInteraction(SOLD_OUT);

        machine.setState(this);

        return SOLD_OUT;
    }

    @Override
    public void selectItem(VendingMachine machine, InventoryItem item) {
        log.logInteraction(SOLD_OUT);

        machine.setState(this);
    }

    @Override
    public String dispense(VendingMachine machine, InventoryItem item) {
        log.logInteraction(SOLD_OUT);

        machine.setState(this);

        return SOLD_OUT;
    }

    @Override
    public String toString() {
        return SOLD_OUT;
    }
}
