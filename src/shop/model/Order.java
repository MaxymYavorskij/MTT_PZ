package shop.model;

import shop.BusinessRules;

import java.util.ArrayList;
import java.util.List;

public class Order implements Entity {

    private int id;

    private final Client client;

    private Manager manager;

    private final List<Product> items = new ArrayList<>();

    public Order(Client client) {
        this.client = client;
        client.getOrders().add(this);
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Client getClient() { return client; }

    public Manager getManager() { return manager; }

    public List<Product> getItems() { return items; }

    public void addItem(Product product) { items.add(product); }

    // сума замовлення рахується за правилом BR-1 (див. BusinessRules)
    public double getTotal() {
        return BusinessRules.calculateOrderTotal(this);
    }

    public boolean isProcessed() { return manager != null; }

    // відповідальна особа опрацьовує замовлення; зв'язок встановлюється з обох боків
    public void assignManager(Manager manager) {
        this.manager = manager;
        manager.getOrders().add(this);
    }

    @Override
    public String toString() {
        return "Замовлення №" + id;
    }
}