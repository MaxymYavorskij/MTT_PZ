package shop.usecase;

import shop.rules.BusinessRules;
import shop.data.ShopRepository;
import shop.model.Client;
import shop.model.Order;
import shop.model.Product;

// ПР-4. Оформити замовлення.
// Реалізовано за UML діаграмою послідовності (Лаб. 6), номери в коментарях = номери повідомлень на діаграмі.
public class PlaceOrderUseCase {

    private final ShopRepository repo;
    private final ViewCatalogUseCase viewCatalog;

    public PlaceOrderUseCase(ShopRepository repo, ViewCatalogUseCase viewCatalog) {
        this.repo = repo;
        this.viewCatalog = viewCatalog;
    }

    // 1: execute(clientId, productIds)
    public Order execute(int clientId, int... productIds) {
        // 2: find(clientId) -> client
        Client client = repo.getClients().find(clientId);

        // alt [client == null] (BR-3: замовляти може тільки зареєстрований клієнт)
        if (client == null) {
            // 3: повідомлення про помилку
            System.out.println("Клієнта з id " + clientId + " не знайдено");
            return null;
        }

        // 4: execute() — показати каталог
        viewCatalog.execute();

        // 5: <<create>> Order(client)
        Order order = new Order(client);

        // loop [для кожного productId]
        for (int productId : productIds) {
            // 6: find(productId) -> product
            Product product = repo.getProducts().find(productId);
            if (product == null) {
                System.out.println("Товар з id " + productId + " не знайдено, пропущено");
            } else {
                // 7: addItem(product)
                order.addItem(product);
            }
        }

        // alt [у замовленні немає товарів]
        if (order.getItems().isEmpty()) {
            client.getOrders().remove(order);
            System.out.println("Замовлення не оформлено: не обрано жодного товару");
            return null;
        }

        // 8: add(order) — зберегти замовлення
        repo.getOrders().add(order);

        // 9: calculateOrderTotal(order) -> total (BR-1)
        double total = BusinessRules.calculateOrderTotal(order);

        // 10: підтвердження клієнту
        System.out.println(order + " оформлено, сума: " + (int) total + " грн");
        return order;
    }
}
