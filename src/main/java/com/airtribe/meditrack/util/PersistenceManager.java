package com.airtribe.meditrack.util;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.AppointmentStatus;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Specialization;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class PersistenceManager {
    private PersistenceManager() {
    }

    private static void ensureDataDir() {
        File dir = new File(Constants.DATA_DIR);
        if (!dir.exists())
            dir.mkdirs();
    }

    public static void savePatients(List<Patient> patients) {
        ensureDataDir();
        List<String[]> rows = new ArrayList<>();
        for (Patient p : patients) {
            rows.add(new String[] { p.getId(), p.getName(), String.valueOf(p.getAge()), p.getContact() });
        }
        try {
            CSVUtil.writeCsv(new File(Constants.PATIENTS_CSV), rows);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save patients: " + e.getMessage(), e);
        }
    }

    public static List<Patient> loadPatients() {
        ensureDataDir();
        File f = new File(Constants.PATIENTS_CSV);
        List<Patient> out = new ArrayList<>();
        if (!f.exists())
            return out;
        try {
            List<String[]> rows = CSVUtil.readCsv(f);
            for (String[] r : rows) {
                if (r.length < 4)
                    continue;
                String id = r[0];
                String name = r[1];
                int age = Integer.parseInt(r[2]);
                String contact = r[3];
                out.add(new Patient(id, name, age, contact));
            }
            return out;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load patients: " + e.getMessage(), e);
        }
    }

    public static void saveDoctors(List<Doctor> doctors) {
        ensureDataDir();
        List<String[]> rows = new ArrayList<>();
        for (Doctor d : doctors) {
            rows.add(new String[] { d.getId(), d.getName(), String.valueOf(d.getAge()), d.getSpecialization().name(),
                    String.valueOf(d.getFee()) });
        }
        try {
            CSVUtil.writeCsv(new File(Constants.DOCTORS_CSV), rows);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save doctors: " + e.getMessage(), e);
        }
    }

    public static List<Doctor> loadDoctors() {
        ensureDataDir();
        File f = new File(Constants.DOCTORS_CSV);
        List<Doctor> out = new ArrayList<>();
        if (!f.exists())
            return out;
        try {
            List<String[]> rows = CSVUtil.readCsv(f);
            for (String[] r : rows) {
                if (r.length < 5)
                    continue;
                String id = r[0];
                String name = r[1];
                int age = Integer.parseInt(r[2]);
                Specialization spec = Specialization.valueOf(r[3]);
                double fee = Double.parseDouble(r[4]);
                out.add(new Doctor(id, name, age, spec, fee));
            }
            return out;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load doctors: " + e.getMessage(), e);
        }
    }

    public static void saveAppointments(List<Appointment> appointments) {
        ensureDataDir();
        List<String[]> rows = new ArrayList<>();
        for (Appointment a : appointments) {
            rows.add(new String[] { a.getId(), a.getPatientId(), a.getDoctorId(), DateUtil.format(a.getWhen()),
                    a.getStatus().name() });
        }
        try {
            CSVUtil.writeCsv(new File(Constants.APPOINTMENTS_CSV), rows);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save appointments: " + e.getMessage(), e);
        }
    }

    public static List<Appointment> loadAppointments() {
        ensureDataDir();
        File f = new File(Constants.APPOINTMENTS_CSV);
        List<Appointment> out = new ArrayList<>();
        if (!f.exists())
            return out;
        try {
            List<String[]> rows = CSVUtil.readCsv(f);
            for (String[] r : rows) {
                if (r.length < 5)
                    continue;
                String id = r[0];
                String patientId = r[1];
                String doctorId = r[2];
                LocalDateTime when = DateUtil.parse(r[3]);
                AppointmentStatus status = AppointmentStatus.valueOf(r[4]);
                Appointment a = new Appointment(id, patientId, doctorId, when);
                a.setStatus(status);
                out.add(a);
            }
            return out;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load appointments: " + e.getMessage(), e);
        }
    }
}
