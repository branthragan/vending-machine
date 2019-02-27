package com.branthragan.vending_machine.state;

import com.branthragan.vending_machine.inventory.InventoryItem;
import com.branthragan.vending_machine.log.TransactionLog;
import com.branthragan.vending_machine.machine.VendingMachine;


public class NoFundsStateImpl implements VendingState {
    private static final String NO_FUNDS = "No Funds";
    private static final String COIN_INSERTED = "Coin inserted.";

    private TransactionLog log;
    private VendingStateManager stateManager;

    NoFundsStateImpl(TransactionLog log, VendingStateManager stateManager) {
        this.log = log;
        this.stateManager = stateManager;
    }

    @Override
    public String insertFunds(VendingMachine machine) {
        String message = COIN_INSERTED + " " + MAKE_A_SELECTION;
        log.logInteraction(message);

        machine.setState(stateManager.getHasFundsState());

        return message;
    }

    @Override
    public String ejectFunds(VendingMachine machine) {
        String message = NO_FUNDS + " " + INVALID_ACTION;
        log.logInteraction(message);

        machine.setState(this);

        return message;
    }

    @Override
    public void selectItem(VendingMachine machine, InventoryItem item) {
        log.logInteraction(NO_FUNDS + " " + INVALID_ACTION);

        machine.setState(this);
    }

    @Override
    public String dispense(VendingMachine machine, InventoryItem item) {
        String message = NO_FUNDS + " " + INVALID_ACTION;
        log.logInteraction(message);

        machine.setState(this);

        return message;
    }

    @Override
    public String toString() {
        return NO_FUNDS;
    }
}
