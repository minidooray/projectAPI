package com.nhnacademy.projectapi.entity;

public enum ProjectStatus {
    ACTIVE("ACTIVE"),
    SLEEP("SLEEP"),
    END("END");

    private final String status;

    ProjectStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
