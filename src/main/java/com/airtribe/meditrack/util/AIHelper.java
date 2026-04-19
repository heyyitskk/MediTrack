package com.airtribe.meditrack.util;

import java.util.Collections;
import java.util.List;

public class AIHelper {
    // Placeholder for optional rule-based recommendations
    public static List<String> recommendSpecializations(String symptoms) {
        if (symptoms == null || symptoms.isBlank())
            return Collections.emptyList();
        // naive rule example
        if (symptoms.toLowerCase().contains("chest"))
            return List.of("CARDIOLOGY");
        if (symptoms.toLowerCase().contains("rash"))
            return List.of("DERMATOLOGY");
        return List.of("GENERAL_PRACTITIONER");
    }
}
