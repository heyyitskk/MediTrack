package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.AppointmentStatus;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.util.PersistenceManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private final DataStore<Appointment> store = new DataStore<>();
    private final AppointmentNotifier notifier = new AppointmentNotifier();

    public AppointmentService() {
        List<Appointment> loaded = PersistenceManager.loadAppointments();
        for (Appointment a : loaded)
            store.save(a.getId(), a);
    }

    public void addListener(AppointmentListener l) {
        notifier.addListener(l);
    }

    public Appointment createAppointment(String patientId, String doctorId, LocalDateTime when) {
        String id = IdGenerator.getInstance().nextId("A");
        Appointment a = new Appointment(id, patientId, doctorId, when);
        store.save(id, a);
        notifier.notifyCreated(a);
        return a;
    }

    public void cancelAppointment(String id) {
        Appointment a = store.get(id);
        if (a != null) {
            a.setStatus(AppointmentStatus.CANCELLED);
            notifier.notifyCancelled(a);
        }
    }

    public void add(Appointment a) {
        store.save(a.getId(), a);
    }

    public Appointment findById(String id) {
        return store.get(id);
    }

    public List<Appointment> all() {
        return store.values();
    }

    public void persist() {
        PersistenceManager.saveAppointments(new ArrayList<>(store.values()));
    }
}
