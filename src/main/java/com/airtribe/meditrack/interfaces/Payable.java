package com.airtribe.meditrack.interfaces;

public interface Payable {
    double calculateAmount();

    default void pay() {
        System.out.println("Payment processed: " + calculateAmount());
    }
}
