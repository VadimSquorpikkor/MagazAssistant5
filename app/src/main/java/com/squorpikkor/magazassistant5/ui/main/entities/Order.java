package com.squorpikkor.magazassistant5.ui.main.entities;

public class Order extends Entity{

    private final int employeeId;
    private int price;
    private int count;
    private boolean isChecked;

    /**Для БД*/
    public Order(int id, String name, int price, int count, int employeeId, boolean isChecked) {
        super(id, name);
        this.price = price;
        this.count = count;
        this.employeeId = employeeId;
        this.isChecked = isChecked;
    }

    //используется в OrderDialog в варианте когда создается новый ордер
    public Order(int employeeId) {
        super(-1, "");
        this.price = 0;
        this.count = 0;
        this.employeeId = employeeId;
        this.isChecked = false;
    }

    //используется в OrderDialog в варианте когда создается новый ордер
    public Order(String name, int employeeId, int price, int count, boolean isChecked) {
        super(-1, name);
        this.employeeId = employeeId;
        this.price = price;
        this.count = count;
        this.isChecked = isChecked;
    }

    public int getEmployeeId() {
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
