package shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order implements Entity {

    private int id;

    private final Client client;

    private Manager manager;

    private final List<Product> items = new ArrayList<>();

    // початковий стан після створення — NEW
    private OrderState state = OrderState.NEW;

    public Order(Client client) {
        this.client = client;
        client.getOrders().add(this);
    }

    public OrderState getState() { return state; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Client getClient() { return client; }

    public Manager getManager() { return manager; }

    public List<Product> getItems() { return items; }

    public void addItem(Product product) { items.add(product); }

    public boolean isProcessed() { return manager != null; }

    // NEW -> PROCESSING: assignManager [state == NEW] / зв'язок з менеджером з обох боків
    public boolean assignManager(Manager manager) {
        if (state != OrderState.NEW) return deny("опрацювати");
        this.manager = manager;
        manager.getOrders().add(this);
        return changeState(OrderState.PROCESSING);
    }

    // PROCESSING -> SHIPPED: ship [state == PROCESSING]
    public boolean ship() {
        if (state != OrderState.PROCESSING) return deny("відправити");
        return changeState(OrderState.SHIPPED);
    }

    // SHIPPED -> COMPLETED: complete [state == SHIPPED]
    public boolean complete() {
        if (state != OrderState.SHIPPED) return deny("завершити");
        return changeState(OrderState.COMPLETED);
    }

    // NEW / PROCESSING -> CANCELLED: cancel [ще не відправлене]
    public boolean cancel() {
        if (state != OrderState.NEW && state != OrderState.PROCESSING) return deny("скасувати");
        return changeState(OrderState.CANCELLED);
    }

    private boolean changeState(OrderState newState) {
        System.out.println(this + ": " + state.getTitle() + " -> " + newState.getTitle());
        state = newState;
        return true;
    }

    private boolean deny(String action) {
        System.out.println(this + ": неможливо " + action + " у стані \"" + state.getTitle() + "\"");
        return false;
    }

    @Override
    public String toString() {
        return "Замовлення №" + id;
    }
}