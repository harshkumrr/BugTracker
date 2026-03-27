package com.bugtracker.bugtracker_backend.service;

import org.springframework.stereotype.Service;

@Service
public class PriorityEngine {

    public String suggestPriority(String description) {
        if (description == null || description.isBlank()) {
            return "Low";
        }

        String lower = description.toLowerCase();

        if (lower.contains("crash") || lower.contains("data loss")
                || lower.contains("security") || lower.contains("breach")
                || lower.contains("corrupted") || lower.contains("system down")) {
            return "Critical";
        }

        if (lower.contains("error") || lower.contains("fail")
                || lower.contains("wrong") || lower.contains("incorrect")
                || lower.contains("broken") || lower.contains("not working")) {
            return "High";
        }

        if (lower.contains("slow") || lower.contains("delay")
                || lower.contains("performance") || lower.contains("timeout")) {
            return "Medium";
        }

        return "Low";
    }
}

