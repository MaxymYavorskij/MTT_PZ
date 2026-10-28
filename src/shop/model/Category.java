package shop.model;

import java.util.ArrayList;
import java.util.List;

public class Category implements Entity {
    private int id;
    private String name;

    private final List<Product> products = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Product> getProducts() { return products; }

    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }
}
