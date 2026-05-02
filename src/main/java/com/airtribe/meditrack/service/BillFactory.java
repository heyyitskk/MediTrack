package com.airtribe.meditrack.service;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Bill;

public final class BillFactory {
    private BillFactory() {
    }

    public static Bill createBill(String id, double baseAmount) {
        return createBill(id, baseAmount, new StandardBillingStrategy());
    }

    public static Bill createBill(String id, double baseAmount, BillingStrategy strategy) {
        double adjusted = strategy.apply(baseAmount);
        double total = adjusted + (adjusted * Constants.TAX_RATE);
        return new Bill(id, total);
    }
}
