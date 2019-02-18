package com.branthragan.vending_machine.log;

public interface TransactionLog {
    void logInteraction(String message);

    void logPurchase(String message);

    void logError(String message);

    void printInteractions();

    void printPurchases();

    void printErrors();
}
