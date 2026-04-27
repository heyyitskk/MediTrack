package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.util.PersistenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatientService {
    private final DataStore<Patient> store = new DataStore<>();

    public PatientService() {
        // load persisted patients on startup
        List<Patient> loaded = PersistenceManager.loadPatients();
        for (Patient p : loaded)
            store.save(p.getId(), p);
    }

    public Patient createPatient(String name, int age, String contact) {
        String id = IdGenerator.getInstance().nextId("P");
        Patient p = new Patient(id, name, age, contact);
        store.save(id, p);
        return p;
    }

    public void addPatient(Patient p) {
        store.save(p.getId(), p);
    }

    public Patient findById(String id) {
        return store.get(id);
    }

    public List<Patient> findByName(String name) {
        if (name == null)
            return List.of();
        return store.values().stream().filter(p -> p.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    public List<Patient> findByAge(int age) {
        return store.values().stream().filter(p -> p.getAge() == age).collect(Collectors.toList());
    }

    public List<Patient> all() {
        return store.values();
    }

    public void remove(String id) {
        store.values().removeIf(p -> p.getId().equals(id));
    }

    public void persist() {
        PersistenceManager.savePatients(new ArrayList<>(store.values()));
    }
}
