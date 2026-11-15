package shop.usecase;

import shop.data.ShopRepository;
import shop.model.Order;

// Переходи замовлення між станами: відправити, отримати, скасувати
public class ChangeOrderStateUseCase {

    private final ShopRepository repo;

    public ChangeOrderStateUseCase(ShopRepository repo) {
        this.repo = repo;
    }

    public void ship(int orderId) {
        Order order = find(orderId);
        if (order != null) order.ship();
    }

    public void complete(int orderId) {
        Order order = find(orderId);
        if (order != null) order.complete();
    }

    public void cancel(int orderId) {
        Order order = find(orderId);
        if (order != null) order.cancel();
    }

    private Order find(int orderId) {
        Order order = repo.getOrders().find(orderId);
        if (order == null) {
            System.out.println("Замовлення з id " + orderId + " не знайдено");
        }
        return order;
    }
}
