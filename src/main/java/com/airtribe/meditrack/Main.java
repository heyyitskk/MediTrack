package com.airtribe.meditrack;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.BillFactory;
import com.airtribe.meditrack.service.BillingStrategy;
import com.airtribe.meditrack.service.DiscountBillingStrategy;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.service.StandardBillingStrategy;
import com.airtribe.meditrack.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("MediTrack - Clinic Management System");
        boolean loadData = false;
        for (String a : args)
            if ("--loadData".equalsIgnoreCase(a))
                loadData = true;

        PatientService ps = new PatientService();
        DoctorService ds = new DoctorService();
        AppointmentService as = new AppointmentService();
        as.addListener(new com.airtribe.meditrack.service.ConsoleAppointmentListener());

        if (loadData) {
            System.out.println("--loadData: patients=" + ps.all().size() + ", doctors=" + ds.all().size()
                    + ", appointments=" + as.all().size());
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("1) Manage Patients");
            System.out.println("2) Manage Doctors");
            System.out.println("3) Appointments");
            System.out.println("4) Billing");
            System.out.println("5) Exit");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();
            switch (c) {
                case "1":
                    managePatients(sc, ps);
                    break;
                case "2":
                    manageDoctors(sc, ds);
                    break;
                case "3":
                    manageAppointments(sc, as, ps, ds);
                    break;
                case "4":
                    manageBilling(sc, as, ds);
                    break;
                case "5":
                    System.out.println("Persisting and exiting.");
                    ps.persist();
                    ds.persist();
                    as.persist();
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void managePatients(Scanner sc, PatientService ps) {
        while (true) {
            System.out.println();
            System.out.println("Patients: 1) Create  2) List  3) Find by id  4) Delete  5) Back");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();
            switch (c) {
                case "1":
                    System.out.print("Name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Age: ");
                    int age = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Contact: ");
                    String contact = sc.nextLine().trim();
                    Patient p = ps.createPatient(name, age, contact);
                    System.out.println("Created: " + p.describe());
                    break;
                case "2":
                    List<Patient> all = ps.all();
                    all.forEach(x -> System.out.println(x.describe()));
                    break;
                case "3":
                    System.out.print("Patient id: ");
                    String id = sc.nextLine().trim();
                    Patient found = ps.findById(id);
                    System.out.println(found == null ? "Not found" : found.describe());
                    break;
                case "4":
                    System.out.print("Patient id to delete: ");
                    String del = sc.nextLine().trim();
                    ps.remove(del);
                    System.out.println("Deleted (if existed).");
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void manageDoctors(Scanner sc, DoctorService ds) {
        while (true) {
            System.out.println();
            System.out.println("Doctors: 1) Create  2) List  3) Find by id  4) Delete  5) Back");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();
            switch (c) {
                case "1":
                    System.out.print("Name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Age: ");
                    int age = Integer.parseInt(sc.nextLine().trim());
                    System.out.println("Specializations: ");
                    for (Specialization s : Specialization.values())
                        System.out.println("  " + s);
                    System.out.print("Spec: ");
                    String spec = sc.nextLine().trim();
                    Specialization sp = Specialization.valueOf(spec.toUpperCase());
                    System.out.print("Fee: ");
                    double fee = Double.parseDouble(sc.nextLine().trim());
                    Doctor d = ds.createDoctor(name, age, sp, fee);
                    System.out.println("Created: " + d.describe());
                    break;
                case "2":
                    ds.all().forEach(x -> System.out.println(x.describe()));
                    break;
                case "3":
                    System.out.print("Doctor id: ");
                    String id = sc.nextLine().trim();
                    Doctor found = ds.findById(id);
                    System.out.println(found == null ? "Not found" : found.describe());
                    break;
                case "4":
                    System.out.print("Doctor id to delete: ");
                    String del = sc.nextLine().trim();
                    ds.remove(del);
                    System.out.println("Deleted (if existed).");
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void manageAppointments(Scanner sc, AppointmentService as, PatientService ps, DoctorService ds) {
        while (true) {
            System.out.println();
            System.out.println("Appointments: 1) Create  2) List  3) Find by id  4) Cancel  5) Back");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();
            switch (c) {
                case "1":
                    System.out.print("Patient id: ");
                    String pid = sc.nextLine().trim();
                    if (ps.findById(pid) == null) {
                        System.out.println("Unknown patient");
                        break;
                    }
                    System.out.print("Doctor id: ");
                    String did = sc.nextLine().trim();
                    if (ds.findById(did) == null) {
                        System.out.println("Unknown doctor");
                        break;
                    }
                    System.out.print("When (ISO local datetime, e.g. 2026-05-03T14:30): ");
                    String when = sc.nextLine().trim();
                    try {
                        LocalDateTime dt = DateUtil.parse(when);
                        Appointment a = as.createAppointment(pid, did, dt);
                        System.out.println("Created: id=" + a.getId() + " status=" + a.getStatus());
                    } catch (Exception e) {
                        System.out.println("Invalid datetime: " + e.getMessage());
                    }
                    break;
                case "2":
                    as.all().forEach(a -> System.out.println(a.getId() + " patient=" + a.getPatientId() + " doctor="
                            + a.getDoctorId() + " when=" + DateUtil.format(a.getWhen()) + " status=" + a.getStatus()));
                    break;
                case "3":
                    System.out.print("Appointment id: ");
                    String id = sc.nextLine().trim();
                    Appointment f = as.findById(id);
                    if (f == null)
                        System.out.println("Not found");
                    else
                        System.out.println(f.getId() + " " + f.getStatus());
                    break;
                case "4":
                    System.out.print("Appointment id to cancel: ");
                    String cid = sc.nextLine().trim();
                    as.cancelAppointment(cid);
                    System.out.println("Cancelled (if existed).");
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void manageBilling(Scanner sc, AppointmentService as, DoctorService ds) {
        System.out.print("Appointment id for billing: ");
        String aid = sc.nextLine().trim();
        Appointment a = as.findById(aid);
        if (a == null) {
            System.out.println("Appointment not found");
            return;
        }
        Doctor d = ds.findById(a.getDoctorId());
        if (d == null) {
            System.out.println("Doctor not found for appointment");
            return;
        }
        double base = d.getFee();
        System.out.println("Doctor fee: " + base);
        System.out.println("Billing strategy: 1) Standard  2) Discount");
        System.out.print("Choose: ");
        String choice = sc.nextLine().trim();
        BillingStrategy strat = new StandardBillingStrategy();
        if ("2".equals(choice)) {
            System.out.print("Discount % (e.g. 10): ");
            double pct = Double.parseDouble(sc.nextLine().trim());
            strat = new DiscountBillingStrategy(pct / 100.0);
        }
        String billId = com.airtribe.meditrack.util.IdGenerator.getInstance().nextId("B");
        Bill bill = BillFactory.createBill(billId, base, strat);
        System.out.println("Created bill id=" + bill.getId() + " amount=" + bill.calculateAmount());
    }
}
