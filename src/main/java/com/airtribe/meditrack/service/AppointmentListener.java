package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;

public interface AppointmentListener {
    void onAppointmentCreated(Appointment a);

    void onAppointmentCancelled(Appointment a);
}
