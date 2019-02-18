package com.branthragan.vending_machine.vending_service;

import com.branthragan.vending_machine.machine.VendingMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendingController {
    private VendingMachine machine;

    @Autowired
    public VendingController(VendingMachine machine) {
        this.machine = machine;
    }

    @GetMapping("/vend")
    public String getAvailableItems(Model model) {
        //TODO return item list & count

        model.addAttribute("count", machine.getCount());
        return "vend";
    }

    @PostMapping("/vend/insert-funds")
    public void insertFunds() {
        machine.insertFunds();
        System.out.println("inserted coin");
    }

    @PostMapping("/vend/eject-funds")
    public void ejectFunds() {

    }

    @PostMapping("/vend/")
    public void selectItem() {

    }

}
