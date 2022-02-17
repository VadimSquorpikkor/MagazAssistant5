package com.squorpikkor.magazassistant5.ui.main.entities;

public class Order extends Entity{

    private final String employeeId;
    private int price;
    private int count;
    private boolean isChecked;

    public Order(String id, String name, int price, int count, String employeeId, boolean isChecked) {
        super(id, name);
        this.price = price;
        this.count = count;
        this.employeeId = employeeId;
        this.isChecked = isChecked;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
