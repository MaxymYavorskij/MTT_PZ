package shop.model;

import java.util.ArrayList;
import java.util.List;

// Абстрактна особа: спільна частина клієнта і відповідальної особи (Лаб. 8, узагальнення)
public abstract class Person implements Entity {
    private int id;
    private String fullName;

    private final List<Order> orders = new ArrayList<>();

    protected Person(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public int getId() { return id; }

    @Override
    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public List<Order> getOrders() { return orders; }

    // роль особи в системі, кожен нащадок визначає свою (поліморфізм)
    public abstract String getRole();

    @Override
    public String toString() {
        return "[" + id + "] " + fullName;
    }
}
