package com.airtribe.meditrack.test;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.ConsoleAppointmentListener;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;

import java.time.LocalDateTime;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("=== MediTrack Manual TestRunner ===");

        PatientService ps = new PatientService();
        DoctorService ds = new DoctorService();
        AppointmentService as = new AppointmentService();

        // register console notifier
        as.addListener(new ConsoleAppointmentListener());

        // create sample doctor and patient
        Doctor doc = ds.createDoctor("Dr. Alice", 45, Specialization.CARDIOLOGY, 300.0);
        Patient pat = ps.createPatient("Bob", 30, "bob@example.com");

        System.out.println("Created doctor: " + doc.describe());
        System.out.println("Created patient: " + pat.describe());

        // create appointment
        Appointment appt = as.createAppointment(pat.getId(), doc.getId(), LocalDateTime.now().plusDays(2));
        System.out.println("Created appointment id=" + appt.getId() + " status=" + appt.getStatus());

        // demonstrate cloning (deep/shallow semantics)
        Patient patClone = pat.clone();
        System.out.println("Cloned patient: " + patClone.describe());
        patClone.setContact("clone+bob@example.com");
        System.out.println("After modifying clone contact -> original contact=" + pat.getContact() + ", clone contact="
                + patClone.getContact());

        Appointment apptClone = appt.clone();
        System.out.println("Cloned appointment id=" + apptClone.getId() + " status=" + apptClone.getStatus());
        apptClone.setStatus(com.airtribe.meditrack.entity.AppointmentStatus.CANCELLED);
        System.out.println("After changing clone status -> original status=" + appt.getStatus() + ", clone status="
                + apptClone.getStatus());

        // persist data to CSV
        ps.persist();
        ds.persist();
        as.persist();

        System.out.println("Saved patients/doctors/appointments to CSV (data folder).");
    }
}
