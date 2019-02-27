package com.branthragan.vending_machine.state;

import com.branthragan.vending_machine.inventory.InventoryItem;
import com.branthragan.vending_machine.machine.VendingMachine;

public interface VendingState {
    String MAKE_A_SELECTION = "Please make a selection";
    String SOLD_OUT = "Sold Out";
    String INVALID_ACTION = "Invalid Action";

    String insertFunds(VendingMachine machine);

    String ejectFunds(VendingMachine machine);

    void selectItem(VendingMachine machine, InventoryItem item);

    String dispense(VendingMachine machine, InventoryItem item);
}
