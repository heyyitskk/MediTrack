package com.airtribe.meditrack;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("MediTrack - Clinic Management System (starter)");
        boolean loadData = false;
        for (String a : args) {
            if ("--loadData".equalsIgnoreCase(a)) {
                loadData = true;
            }
        }
        if (loadData) {
            System.out.println("--loadData detected: CSV load will run at startup (not yet implemented).");
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("1) Manage Patients");
            System.out.println("2) Manage Doctors");
            System.out.println("3) Appointments");
            System.out.println("4) Billing");
            System.out.println("5) Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println("Patients menu (TBD)");
                    break;
                case "2":
                    System.out.println("Doctors menu (TBD)");
                    break;
                case "3":
                    System.out.println("Appointments (TBD)");
                    break;
                case "4":
                    System.out.println("Billing (TBD)");
                    break;
                case "5":
                    System.out.println("Exiting MediTrack. Goodbye.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
