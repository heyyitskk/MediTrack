package com.airtribe.meditrack.service;

public class StandardBillingStrategy implements BillingStrategy {
    @Override
    public double apply(double baseAmount) {
        return baseAmount;
    }
}
