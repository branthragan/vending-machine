package com.branthragan.vending_machine;

import com.branthragan.vending_machine.demo.DemoVendingMachine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendingMachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendingMachineApplication.class, args);

        DemoVendingMachine.runStateDemo();
        DemoVendingMachine.runDemoToEmpty();
    }
}
