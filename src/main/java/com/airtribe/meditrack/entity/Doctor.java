package com.airtribe.meditrack.entity;

public class Doctor extends Person {
    private Specialization specialization;
    private double fee;

    public Doctor(String id, String name, int age, Specialization specialization, double fee) {
        super(id, name, age);
        this.specialization = specialization;
        this.fee = fee;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public double getFee() {
        return fee;
    }

    @Override
    public String describe() {
        return String.format("Doctor[id=%s,name=%s,spec=%s,fee=%.2f]", id, name, specialization, fee);
    }
}
