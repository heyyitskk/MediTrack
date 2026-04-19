package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.util.DataStore;

import java.util.List;

public class DoctorService {
    private final DataStore<Doctor> store = new DataStore<>();

    public void addDoctor(Doctor d) {
        store.save(d.getId(), d);
    }

    public Doctor findById(String id) {
        return store.get(id);
    }

    public List<Doctor> all() {
        return store.values();
    }
}
