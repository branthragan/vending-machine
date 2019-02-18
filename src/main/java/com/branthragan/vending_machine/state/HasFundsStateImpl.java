package com.branthragan.vending_machine.state;

import com.branthragan.vending_machine.log.TransactionLog;
import com.branthragan.vending_machine.machine.VendingMachine;


public class HasFundsStateImpl implements VendingState {
    private static final String MAKE_A_SELECTION = "You have already have one credit. Please make a selection";
    private static final String EJECT_COIN = "Eject coin. Please come back soon.";

    private TransactionLog log;
    private VendingStateManager stateManager;

    HasFundsStateImpl(TransactionLog log, VendingStateManager stateManager) {
        this.log = log;
        this.stateManager = stateManager;
    }


    @Override
    public void insertFunds(VendingMachine machine) {
        log.logInteraction(MAKE_A_SELECTION);

        machine.setState(this);
    }

    @Override
    public void ejectFunds(VendingMachine machine) {
        log.logInteraction(EJECT_COIN);

        machine.setState(stateManager.getNoFundsState());
    }

    @Override
    public void selectItem(VendingMachine machine, String item) {
        String message = String.format("%s selected", item);
        log.logInteraction(message);

        if (machine.hasItemInInventory(item)) {
            machine.setState(stateManager.getItemSoldState());
        } else {
            String unableToDispense = String.format("Unable to dispense %s Please make another selection", item);
            log.logError(unableToDispense);

            machine.setState(this);
        }

    }

    @Override
    public void dispense(VendingMachine machine, String item) {
        log.logInteraction(INVALID_ACTION);

        machine.setState(this);
    }

    @Override
    public String toString() {
        return "Has Funds";
    }
}
