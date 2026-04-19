package com.airtribe.meditrack.entity;

public final class BillSummary {
    private final String billId;
    private final String patientId;
    private final double totalAmount;

    public BillSummary(String billId, String patientId, double totalAmount) {
        this.billId = billId;
        this.patientId = patientId;
        this.totalAmount = totalAmount;
    }

    public String getBillId() {
        return billId;
    }

    public String getPatientId() {
        return patientId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return String.format("BillSummary[billId=%s,patientId=%s,total=%.2f]", billId, patientId, totalAmount);
    }
}
