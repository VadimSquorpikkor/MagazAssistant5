package com.squorpikkor.magazassistant5.ui.main.entities;

public class Employee extends Entity{

    private final String locationId;

    public Employee(String id, String name, String employeeId) {
        super(id, name);
        this.locationId = employeeId;
    }

    public String getLocationId() {
        return locationId;
    }
}
