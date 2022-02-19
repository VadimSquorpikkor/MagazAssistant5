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

    //используется в OrderDialog в варианте когда создается новый ордер
    public Order(String id, String employeeId) {
        super(id, "");
        this.price = 0;
        this.count = 0;
        this.employeeId = employeeId;
        this.isChecked = false;
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

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
