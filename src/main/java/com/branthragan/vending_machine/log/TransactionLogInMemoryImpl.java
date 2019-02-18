package com.branthragan.vending_machine.log;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("TransactionLogInMemory")
@Scope("singleton")
public class TransactionLogInMemoryImpl implements TransactionLog {
    private List<String> interactions = new ArrayList<>();
    private List<String> purchases = new ArrayList<>();
    private List<String> errors = new ArrayList<>();

    @Override
    public void logInteraction(String message) {
        System.out.println(message);
        this.interactions.add(message);
    }

    @Override
    public void logPurchase(String message) {
        System.out.println(message);
        this.purchases.add(message);
    }

    @Override
    public void logError(String message) {
        System.out.println(message);
        this.errors.add(message);
    }

    @Override
    public void printInteractions() {
        this.interactions.forEach(System.out::println);
    }

    @Override
    public void printPurchases() {
        this.purchases.forEach(System.out::println);
    }

    @Override
    public void printErrors() {
        this.errors.forEach(System.out::println);
    }
}
