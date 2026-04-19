package com.airtribe.meditrack.entity;

public class Person extends MedicalEntity {
    protected int age;

    public Person(String id, String name, int age) {
        super(id, name);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String describe() {
        return String.format("Person[id=%s,name=%s,age=%d]", id, name, age);
    }
}
