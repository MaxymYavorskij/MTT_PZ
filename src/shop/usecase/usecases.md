# Use Cases

## ViewCatalogUseCase

```java
package shop.usecase;

import shop.data.ShopRepository;
import shop.model.Category;
import shop.model.Product;

import java.util.stream.Collectors;

public class ViewCatalogUseCase {

    private final ShopRepository repo;

    public ViewCatalogUseCase(ShopRepository repo) {
        this.repo = repo;
    }

    public void execute() {
        for (Product product : repo.getProducts().getRows()) {
            String categories = product.getCategories().stream()
                    .map(Category::getName)
                    .collect(Collectors.joining(", "));
            System.out.println(product + " (" + categories + ")");
        }
    }
}
```

## ViewProductsByCategoryUseCase

```java
package shop.usecase;

import shop.data.ShopRepository;
import shop.model.Category;
import shop.model.Product;

public class ViewProductsByCategoryUseCase {

    private final ShopRepository repo;

    public ViewProductsByCategoryUseCase(ShopRepository repo) {
        this.repo = repo;
    }

    public void execute(int categoryId) {
        Category category = repo.getCategories().find(categoryId);
        if (category == null) {
            System.out.println("Категорію з id " + categoryId + " не знайдено");
            return;
        }

        System.out.println("Категорія " + category + ":");
        for (Product product : category.getProducts()) {
            System.out.println("\t" + product);
        }
    }
}
```

## ViewProductInfoUseCase

```java
package shop.usecase;

import shop.data.ShopRepository;
import shop.model.Product;

public class ViewProductInfoUseCase {

    private final ShopRepository repo;

    public ViewProductInfoUseCase(ShopRepository repo) {
        this.repo = repo;
    }

    public void execute(int productId) {
        Product product = repo.getProducts().find(productId);
        if (product == null) {
            System.out.println("Товар з id " + productId + " не знайдено");
            return;
        }

        System.out.println(product);
        System.out.println("\tОпис: " + product.getInfo().getText());
    }
}
```

## PlaceOrderUseCase

```java
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
```

## ViewClientOrdersUseCase

```java
package shop.usecase;

import shop.data.ShopRepository;
import shop.model.Client;
import shop.model.Order;

public class ViewClientOrdersUseCase {

    private final ShopRepository repo;

    public ViewClientOrdersUseCase(ShopRepository repo) {
        this.repo = repo;
    }

    public void execute(int clientId) {
        Client client = repo.getClients().find(clientId);
        if (client == null) {
            System.out.println("Клієнта з id " + clientId + " не знайдено");
            return;
        }

        System.out.println("Замовлення клієнта " + client + ":");
        if (client.getOrders().isEmpty()) {
            System.out.println("\tзамовлень немає");
        }

        for (Order order : client.getOrders()) {
            String status = order.isProcessed() ? "опрацьовано" : "очікує опрацювання";
            System.out.println("\t" + order + ", сума: " + (int) order.getTotal() + " грн, " + status);
        }
    }
}
```

## ProcessOrderUseCase

```java
package shop.usecase;

import shop.data.ShopRepository;
import shop.model.Manager;
import shop.model.Order;

public class ProcessOrderUseCase {

    private final ShopRepository repo;
    private final ViewAllOrdersUseCase viewAllOrders;

    public ProcessOrderUseCase(ShopRepository repo, ViewAllOrdersUseCase viewAllOrders) {
        this.repo = repo;
        this.viewAllOrders = viewAllOrders;
    }

    public void execute(int managerId, int orderId) {
        Manager manager = repo.getManagers().find(managerId);
        if (manager == null) {
            System.out.println("Відповідальну особу з id " + managerId + " не знайдено");
            return;
        }

        viewAllOrders.execute();

        Order order = repo.getOrders().find(orderId);
        if (order == null) {
            System.out.println("Замовлення з id " + orderId + " не знайдено");
            return;
        }

        if (order.isProcessed()) {
            System.out.println(order + " вже опрацьовано: " + order.getManager().getFullName());
            return;
        }

        order.assignManager(manager);
        System.out.println(order + " опрацьовує " + manager.getFullName());
    }
}
```

## ViewAllOrdersUseCase

```java
package shop.usecase;

import shop.data.ShopRepository;
import shop.model.Order;

public class ViewAllOrdersUseCase {

    private final ShopRepository repo;

    public ViewAllOrdersUseCase(ShopRepository repo) {
        this.repo = repo;
    }

    public void execute() {
        if (repo.getOrders().getRows().isEmpty()) {
            System.out.println("Замовлень немає");
            return;
        }

        for (Order order : repo.getOrders().getRows()) {
            String status = order.isProcessed()
                    ? "відповідальний — " + order.getManager().getFullName()
                    : "очікує опрацювання";
            System.out.println(order + ": клієнт — " + order.getClient().getFullName()
                    + ", сума: " + (int) order.getTotal() + " грн, " + status);
        }
    }
}
```

