package com.branthragan.vending_machine.vending_service;

import com.branthragan.vending_machine.demo.DemoVendingMachine;
import com.branthragan.vending_machine.inventory.InventoryItem;
import com.branthragan.vending_machine.machine.VendingMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VendingController {
    private VendingMachine machine;
    private VendingService service;

    @Autowired
    public VendingController(VendingMachine machine, VendingService service) {
        this.machine = machine;
        this.service = service;
    }

    @GetMapping("/vend")
    public List<InventoryItem> getAvailableItems(Model model) {
        List<InventoryItem> inventory = service.getInventory();

        model.addAttribute("count", machine.getCount());
        return inventory;
    }

    @PostMapping("/vend/{id}")
    public String selectItem(@PathVariable("id") Long id) {
        InventoryItem item = machine.selectItem(id);

        if (item != null) {
            return "Grab your " + item.getName() + ". " + item.getCount() + " remaining.";
        } else {
            return "Please make another selection";
        }

    }

    @PostMapping("/vend/insert-funds")
    public String insertFunds() {
        machine.insertFunds();

        return machine.getState().toString();
    }

    @PostMapping("/vend/eject-funds")
    public String ejectFunds() {
        machine.ejectFunds();

        return machine.getState().toString();
    }

    @GetMapping("/vend/demo")
    public void runDemo() {
        DemoVendingMachine demo = new DemoVendingMachine(this.machine);

        demo.runStateDemo();
        demo.runDemoToEmpty();
    }

}
