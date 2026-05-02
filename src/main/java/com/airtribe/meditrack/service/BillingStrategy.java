package com.airtribe.meditrack.service;

public interface BillingStrategy {
    double apply(double baseAmount);
}
