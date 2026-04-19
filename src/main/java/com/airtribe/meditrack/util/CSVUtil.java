package com.airtribe.meditrack.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
    public static List<String[]> readCsv(File file) throws IOException {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = r.readLine()) != null) {
                rows.add(line.split(","));
            }
        }
        return rows;
    }

    public static void writeCsv(File file, List<String[]> rows) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(file))) {
            for (String[] row : rows) {
                w.write(String.join(",", row));
                w.newLine();
            }
        }
    }
}
