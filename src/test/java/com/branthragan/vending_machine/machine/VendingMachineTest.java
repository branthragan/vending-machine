package com.branthragan.vending_machine.machine;

import com.branthragan.vending_machine.state.VendingStateManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class VendingMachineTest {

    @Autowired
    private VendingMachine machine;

    @Autowired
    private VendingStateManager manager;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testState_SoldOut() {
        machine.setState(manager.getSoldOutState());

        machine.insertFunds();
        machine.ejectFunds();
        machine.selectItem(1001L);
    }

    @Test
    void testState_HasFunds() {
        machine.setState(manager.getHasFundsState());

        machine.insertFunds();
        machine.ejectFunds();
        machine.selectItem(1001L);
    }

    @Test
    void testState_NoFunds() {
        machine.setState(manager.getNoFundsState());

        machine.insertFunds();
        machine.ejectFunds();
        machine.selectItem(1001L);
    }

    @Test
    void testState_ItemSold() {
        machine.setState(manager.getItemSoldState());

        machine.insertFunds();
        machine.ejectFunds();
        machine.selectItem(1001L);
    }
}