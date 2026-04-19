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
        try {
            Appointment a = (Appointment) super.clone();
            // LocalDateTime is immutable; deep clone not required here
            return a;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
