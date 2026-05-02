package com.airtribe.meditrack.entity;

import java.time.LocalDateTime;

public class Appointment implements Cloneable {
    private String id;
    private String patientId;
    private String doctorId;
    private LocalDateTime when;
    private AppointmentStatus status;

    public Appointment(String id, String patientId, String doctorId, LocalDateTime when) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.when = when;
        this.status = AppointmentStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    @Override
    public Appointment clone() {
        // create a new Appointment instance with a fresh id
        String newId = com.airtribe.meditrack.util.IdGenerator.getInstance().nextId("A");
        Appointment a = new Appointment(newId, this.patientId, this.doctorId, this.when);
        a.setStatus(this.status);
        return a;
    }
}
