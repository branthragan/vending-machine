package com.branthragan.vending_machine.vending_service;

import com.branthragan.vending_machine.demo.DemoVendingMachine;
import com.branthragan.vending_machine.inventory.InventoryItem;
import com.branthragan.vending_machine.machine.VendingMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
    public Response selectItem(@PathVariable("id") Long id) {
        InventoryItem item = machine.selectItem(id);

        Response response = new Response();
        if (item != null) {
            response.setMessage("Grab your " + item.getName() + ". " + item.getCount() + " remaining.");
        } else {
            response.setMessage("Please make another selection");
        }

        return response;
    }

    @PostMapping("/vend/insert-funds")
    public Response insertFunds() {
        machine.insertFunds();

        return new Response(machine.getState().toString());
    }

    @PostMapping("/vend/eject-funds")
    public Response ejectFunds() {
        machine.ejectFunds();

        return new Response(machine.getState().toString());
    }

    @GetMapping("/vend/demo")
    public void runDemo() {
        DemoVendingMachine demo = new DemoVendingMachine(this.machine);

        demo.runStateDemo();
        demo.runDemoToEmpty();
    }

}
