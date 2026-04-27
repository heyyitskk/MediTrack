package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentNotifier {
    private final List<AppointmentListener> listeners = new ArrayList<>();

    public void addListener(AppointmentListener l) {
        listeners.add(l);
    }

    public void removeListener(AppointmentListener l) {
        listeners.remove(l);
    }

    public void notifyCreated(Appointment a) {
        for (AppointmentListener l : listeners)
            l.onAppointmentCreated(a);
    }

    public void notifyCancelled(Appointment a) {
        for (AppointmentListener l : listeners)
            l.onAppointmentCancelled(a);
    }
}
