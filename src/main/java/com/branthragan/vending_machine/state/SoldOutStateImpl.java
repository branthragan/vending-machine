package com.branthragan.vending_machine.state;

import com.branthragan.vending_machine.log.TransactionLog;
import com.branthragan.vending_machine.machine.VendingMachine;


public class SoldOutStateImpl implements VendingState {
    private TransactionLog log;

    SoldOutStateImpl(TransactionLog log) {
        this.log = log;
    }

    @Override
    public void insertFunds(VendingMachine machine) {
        log.logInteraction(SOLD_OUT);

        machine.setState(this);
    }

    @Override
    public void ejectFunds(VendingMachine machine) {
        log.logInteraction(SOLD_OUT);

        machine.setState(this);
    }

    @Override
    public void selectItem(VendingMachine machine, String item) {
        log.logInteraction(SOLD_OUT);

        machine.setState(this);
    }

    @Override
    public void dispense(VendingMachine machine, String item) {
        log.logInteraction(SOLD_OUT);

        machine.setState(this);
    }

    @Override
    public String toString() {
        return "Sold Out";
    }
}
