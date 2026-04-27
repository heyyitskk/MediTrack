package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;

public class ConsoleAppointmentListener implements AppointmentListener {
    @Override
    public void onAppointmentCreated(Appointment a) {
        System.out.println("[Notifier] Appointment created: " + a.getId() + " for patient " + a.getPatientId());
    }

    @Override
    public void onAppointmentCancelled(Appointment a) {
        System.out.println("[Notifier] Appointment cancelled: " + a.getId());
    }
}
