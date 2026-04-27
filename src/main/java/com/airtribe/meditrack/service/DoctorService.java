package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.util.PersistenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorService {
    private final DataStore<Doctor> store = new DataStore<>();

    public DoctorService() {
        // load persisted doctors
        List<Doctor> loaded = PersistenceManager.loadDoctors();
        for (Doctor d : loaded)
            store.save(d.getId(), d);
    }

    public Doctor createDoctor(String name, int age, Specialization spec, double fee) {
        String id = IdGenerator.getInstance().nextId("D");
        Doctor d = new Doctor(id, name, age, spec, fee);
        store.save(id, d);
        return d;
    }

    public void addDoctor(Doctor d) {
        store.save(d.getId(), d);
    }

    public Doctor findById(String id) {
        return store.get(id);
    }

    public List<Doctor> findBySpecialization(Specialization spec) {
        if (spec == null)
            return List.of();
        return store.values().stream().filter(d -> d.getSpecialization() == spec).collect(Collectors.toList());
    }

    public List<Doctor> all() {
        return store.values();
    }

    public void remove(String id) {
        store.values().removeIf(d -> d.getId().equals(id));
    }

    public void persist() {
        PersistenceManager.saveDoctors(new ArrayList<>(store.values()));
    }
}
