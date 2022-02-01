package com.squorpikkor.magazassistant5.ui.main.entities;

public class Order extends Entity{

    private final String employeeId;

    public Order(String id, String name, String employeeId) {
        super(id, name);
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }
}
