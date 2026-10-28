package shop.model;

import java.util.ArrayList;
import java.util.List;

public class Product implements Entity {
    private int id;
    private String title;
    private double cost;

    private ProductInfo info;

    private final List<Category> categories = new ArrayList<>();

    public Product(String title, double cost) {
        this.title = title;
        this.cost = cost;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public ProductInfo getInfo() { return info; }

    void setInfo(ProductInfo info) { this.info = info; }

    public List<Category> getCategories() { return categories; }

    public void addCategory(Category category) {
        if (!categories.contains(category)) {
            categories.add(category);
            category.getProducts().add(this);
        }
    }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " — " + (int) cost + " грн";
    }
}
