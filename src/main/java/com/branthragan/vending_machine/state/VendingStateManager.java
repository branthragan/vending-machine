package com.branthragan.vending_machine.state;

import com.branthragan.vending_machine.log.TransactionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class VendingStateManager {
    private TransactionLog log;
    private VendingState soldOutState;
    private VendingState hasFundsState;
    private VendingState noFundsState;
    private VendingState itemSoldState;

    @Autowired
    public VendingStateManager(TransactionLog log) {
        this.log = log;
    }

    public VendingState getSoldOutState() {
        if (null == soldOutState) {
            this.soldOutState = new SoldOutStateImpl(log);
        }
        return this.soldOutState;
    }

    public VendingState getHasFundsState() {
        if (null == hasFundsState) {
            this.hasFundsState = new HasFundsStateImpl(log, this);
        }

        return this.hasFundsState;
    }

    public VendingState getNoFundsState() {
        if (null == noFundsState) {
            this.noFundsState = new NoFundsStateImpl(log, this);
        }

        return this.noFundsState;
    }

    public VendingState getItemSoldState() {
        if (null == itemSoldState) {
            this.itemSoldState = new ItemSoldStateImpl(log, this);
        }

        return this.itemSoldState;
    }
}
