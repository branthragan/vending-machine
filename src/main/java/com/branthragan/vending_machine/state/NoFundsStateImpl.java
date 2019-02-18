package com.branthragan.vending_machine.state;

import com.branthragan.vending_machine.log.TransactionLog;
import com.branthragan.vending_machine.machine.VendingMachine;


public class NoFundsStateImpl implements VendingState {
    private TransactionLog log;
    private VendingStateManager stateManager;

    NoFundsStateImpl(TransactionLog log, VendingStateManager stateManager) {
        this.log = log;
        this.stateManager = stateManager;
    }

    @Override
    public void insertFunds(VendingMachine machine) {
        String coinInserted = "Coin inserted";
        log.logInteraction(coinInserted);

        machine.setState(stateManager.getHasFundsState());
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
        log.logInteraction(INVALID_ACTION);

        machine.setState(this);
    }
}
