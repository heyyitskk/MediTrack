package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.util.DataStore;

import java.util.List;

public class AppointmentService {
    private final DataStore<Appointment> store = new DataStore<>();

    public void add(Appointment a) {
        store.save(a.getId(), a);
    }

    public Appointment findById(String id) {
        return store.get(id);
    }

    public List<Appointment> all() {
        return store.values();
    }
}
