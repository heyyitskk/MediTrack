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
        // create a new Patient instance with a new id to avoid id collisions
        String newId = com.airtribe.meditrack.util.IdGenerator.getInstance().nextId("P");
        return new Patient(newId, this.name, this.age, this.contact);
    }

    @Override
    public String describe() {
        return String.format("Patient[id=%s,name=%s,age=%d,contact=%s]", id, name, age, contact);
    }
}
