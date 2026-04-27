package com.airtribe.meditrack.service;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Bill;

public final class BillFactory {
    private BillFactory() {
    }

    public static Bill createBill(String id, double baseAmount) {
        double total = baseAmount + (baseAmount * Constants.TAX_RATE);
        return new Bill(id, total);
    }
}
