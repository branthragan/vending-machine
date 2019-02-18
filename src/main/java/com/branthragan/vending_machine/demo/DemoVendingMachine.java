package com.branthragan.vending_machine.demo;

import com.branthragan.vending_machine.inventory.InventoryItem;
import com.branthragan.vending_machine.machine.VendingMachine;

import java.util.Map;
import java.util.Optional;

public final class DemoVendingMachine {

    private VendingMachine machine;

    public DemoVendingMachine(VendingMachine machine) {
        this.machine = machine;
    }

    public void runStateDemo() {
        machine.insertFunds();
        machine.selectItem(1001L);

        machine.insertFunds();
        machine.ejectFunds();

        //Fail test
        machine.selectItem(1001L);

        //Fail test
        machine.ejectFunds();

        //Fail test
        machine.insertFunds();
        machine.ejectFunds();
        machine.selectItem(1001L);

        System.out.println("Items remaining: " + machine.getCount());
        System.out.println("Finished state demo.\n");
    }

    public void runDemoToEmpty() {
        for (int i = machine.getCount() + 1; i > 0; i--) {
            machine.insertFunds();

            Optional<InventoryItem> itemOptional = machine.getInventory().entrySet().stream()
                    .filter(entry -> entry.getValue().getCount() > 0)
                    .map(Map.Entry::getValue)
                    .findFirst();
            itemOptional.ifPresent(item -> machine.selectItem(item.getId()));

            System.out.println("Items remaining: " + machine.getCount());
        }

        System.out.println("Finished run to empty demo.");
        System.out.println("Final inventory:");

        machine.getInventory().forEach((id, item) ->
                System.out.println("Item: " + item.getId() + " (" + item.getName() + ") Count: " + item.getCount()));
    }
}
