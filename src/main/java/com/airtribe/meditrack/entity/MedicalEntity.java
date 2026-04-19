package com.airtribe.meditrack.entity;

public abstract class MedicalEntity {
    protected String id;
    protected String name;

    public MedicalEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract String describe();
}
