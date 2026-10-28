package shop.usecase;

import shop.data.ShopRepository;
import shop.model.Client;
import shop.model.Order;
import shop.model.Product;

public class PlaceOrderUseCase {

    private final ShopRepository repo;
    private final ViewCatalogUseCase viewCatalog;

    public PlaceOrderUseCase(ShopRepository repo, ViewCatalogUseCase viewCatalog) {
        this.repo = repo;
        this.viewCatalog = viewCatalog;
    }

    public Order execute(int clientId, int... productIds) {
        Client client = repo.getClients().find(clientId);
        if (client == null) {
            System.out.println("Клієнта з id " + clientId + " не знайдено");
            return null;
        }

        viewCatalog.execute();

        Order order = new Order(client);
        for (int productId : productIds) {
            Product product = repo.getProducts().find(productId);
            if (product == null) {
                System.out.println("Товар з id " + productId + " не знайдено, пропущено");
            } else {
                order.addItem(product);
            }
        }

        if (order.getItems().isEmpty()) {
            client.getOrders().remove(order);
            System.out.println("Замовлення не оформлено: не обрано жодного товару");
            return null;
        }

        repo.getOrders().add(order);
        System.out.println(order + " оформлено, сума: " + (int) order.getTotal() + " грн");
        return order;
    }
}
