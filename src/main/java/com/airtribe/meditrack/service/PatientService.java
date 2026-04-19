package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.util.DataStore;

import java.util.List;

public class PatientService {
    private final DataStore<Patient> store = new DataStore<>();

    public void addPatient(Patient p) {
        store.save(p.getId(), p);
    }

    public Patient findById(String id) {
        return store.get(id);
    }

    public List<Patient> all() {
        return store.values();
    }
}
