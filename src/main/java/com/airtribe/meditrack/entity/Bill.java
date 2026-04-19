package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfaces.Payable;

public class Bill implements Payable {
    private String id;
    private double amount;

    public Bill(String id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    @Override
    public double calculateAmount() {
        return amount;
    }
}
