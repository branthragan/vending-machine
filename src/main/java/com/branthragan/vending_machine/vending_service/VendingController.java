package com.branthragan.vending_machine.vending_service;

import com.branthragan.vending_machine.demo.DemoVendingMachine;
import com.branthragan.vending_machine.inventory.InventoryItem;
import com.branthragan.vending_machine.machine.VendingMachine;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<InventoryItem> getAvailableItems() {

        return service.getInventory();
    }

    @PostMapping("/vend/{id}")
    public Response selectItem(@PathVariable("id") Long id) {
        String message = machine.selectItem(id);

        return new Response(message);
    }

    @PostMapping("/vend/insert-funds")
    public Response insertFunds() {
        String message = machine.insertFunds();

        return new Response(message);
    }

    @PostMapping("/vend/eject-funds")
    public Response ejectFunds() {
        String message = machine.ejectFunds();

        return new Response(message);
    }

    @GetMapping("/vend/demo")
    public void runDemo() {
        DemoVendingMachine demo = new DemoVendingMachine(this.machine);

        demo.runStateDemo();
        demo.runDemoToEmpty();
    }

}
