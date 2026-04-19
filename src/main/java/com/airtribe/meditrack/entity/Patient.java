package com.airtribe.meditrack.entity;

public class Patient extends Person implements Cloneable {
    private String contact;

    public Patient(String id, String name, int age, String contact) {
        super(id, name, age);
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public Patient clone() {
        try {
            return (Patient) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public String describe() {
        return String.format("Patient[id=%s,name=%s,age=%d,contact=%s]", id, name, age, contact);
    }
}
