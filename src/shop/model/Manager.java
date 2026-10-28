package shop.model;

import java.util.ArrayList;
import java.util.List;

public class Manager implements Entity {
    private int id;
    private String fullName;

    private final List<Order> orders = new ArrayList<>();

    public Manager(String fullName) {
        this.fullName = fullName;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public List<Order> getOrders() { return orders; }

    @Override
    public String toString() {
        return "[" + id + "] " + fullName;
    }
}
