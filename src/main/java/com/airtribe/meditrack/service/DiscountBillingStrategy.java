package com.airtribe.meditrack.service;

public class DiscountBillingStrategy implements BillingStrategy {
    private final double discountFraction; // 0.2 for 20%

    public DiscountBillingStrategy(double discountFraction) {
        if (discountFraction < 0 || discountFraction > 1)
            throw new IllegalArgumentException("discountFraction must be between 0 and 1");
        this.discountFraction = discountFraction;
    }

    @Override
    public double apply(double baseAmount) {
        return baseAmount * (1.0 - discountFraction);
    }
}
