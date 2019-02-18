package com.branthragan.vending_machine.state;

import com.branthragan.vending_machine.inventory.InventoryItem;
import com.branthragan.vending_machine.machine.VendingMachine;

public interface VendingState {
    String SOLD_OUT = "Sold Out";
    String INVALID_ACTION = "Invalid Action";

    void insertFunds(VendingMachine machine);

    void ejectFunds(VendingMachine machine);

    void selectItem(VendingMachine machine, InventoryItem item);

    void dispense(VendingMachine machine, InventoryItem item);
}
