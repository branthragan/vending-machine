package com.branthragan.vending_machine.state;

import com.branthragan.vending_machine.inventory.InventoryItem;
import com.branthragan.vending_machine.log.TransactionLog;
import com.branthragan.vending_machine.machine.VendingMachine;


public class HasFundsStateImpl implements VendingState {
    private static final String HAS_FUNDS = "Has Funds";
    private static final String ALREADY_HAVE_CREDIT = "You have already have one credit.";
    private static final String EJECT_COIN = "Eject coin. Please come back soon.";

    private TransactionLog log;
    private VendingStateManager stateManager;

    HasFundsStateImpl(TransactionLog log, VendingStateManager stateManager) {
        this.log = log;
        this.stateManager = stateManager;
    }


    @Override
    public String insertFunds(VendingMachine machine) {
        String message = ALREADY_HAVE_CREDIT + " " + MAKE_A_SELECTION;
        log.logInteraction(message);

        machine.setState(this);

        return message;
    }

    @Override
    public String ejectFunds(VendingMachine machine) {
        String message = EJECT_COIN;
        log.logInteraction(message);

        machine.setState(stateManager.getNoFundsState());

        return message;
    }

    @Override
    public void selectItem(VendingMachine machine, InventoryItem item) {
        log.logInteraction(String.format("%s selected", item.getName()));

        if (machine.hasItemInInventory(item.getId())) {
            machine.setState(stateManager.getItemSoldState());
        } else {
            log.logError(String.format("Unable to dispense %s Please make another selection", item.getName()));

            machine.setState(this);
        }

    }

    @Override
    public String dispense(VendingMachine machine, InventoryItem item) {
        String message = item.getName() + " is currently unavailable. " + MAKE_A_SELECTION;
        log.logInteraction(message);

        machine.setState(this);

        return message;
    }

    @Override
    public String toString() {
        return HAS_FUNDS;
    }
}
