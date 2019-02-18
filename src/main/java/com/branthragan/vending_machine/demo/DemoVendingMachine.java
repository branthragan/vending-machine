package com.branthragan.vending_machine.demo;

import com.branthragan.vending_machine.log.TransactionLog;
import com.branthragan.vending_machine.log.TransactionLogInMemoryImpl;
import com.branthragan.vending_machine.machine.VendingMachine;
import com.branthragan.vending_machine.state.VendingStateManager;

public final class DemoVendingMachine {

    public static void runStateDemo() {
        TransactionLog log = new TransactionLogInMemoryImpl();

        VendingMachine machine = new VendingMachine(new VendingStateManager(log), log);

        machine.insertFunds();
        machine.selectItem("Beverage1");

        machine.insertFunds();
        machine.ejectFunds();

        //Fail test
        machine.selectItem("Beverage1");

        //Fail test
        machine.ejectFunds();

        //Fail test
        machine.insertFunds();
        machine.ejectFunds();
        machine.selectItem("Beverage1");

        System.out.println("Items remaining: " + machine.getCount());
        System.out.println("Finished state demo.\n");
    }

    public static void runDemoToEmpty() {
        TransactionLog log = new TransactionLogInMemoryImpl();

        VendingMachine machine = new VendingMachine(new VendingStateManager(log), log);

        for (int i = machine.getCount() + 1; i > 0; i--) {
            machine.insertFunds();
            machine.selectItem("Beverage1");

            System.out.println("Items remaining: " + machine.getCount());
        }

        System.out.println("Finished run to empty demo.");
        System.out.println("Final inventory:");

        machine.getInventory().forEach((item, count) ->
                System.out.println("Item: " + item + " Count: " + count));
    }
}
