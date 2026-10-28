package shop.service;

import shop.data.ShopRepository;
import shop.model.Category;
import shop.model.Client;
import shop.model.Order;
import shop.model.Product;

import java.util.stream.Collectors;

public class ShopReportService {
    private final ShopRepository repo;

    public ShopReportService(ShopRepository repo) {
        this.repo = repo;
    }

    public void showProducts() {
        for (Product product : repo.getProducts().getRows()) {
            System.out.println(product);

            System.out.println("\tОпис: " + product.getInfo().getText());

            String categories = product.getCategories().stream()
                    .map(Category::getName)
                    .collect(Collectors.joining(", "));
            System.out.println("\tКатегорії: " + categories);
        }
    }

    public void showProductsByCategory() {
        for (Category category : repo.getCategories().getRows()) {
            String products = category.getProducts().stream()
                    .map(Product::getTitle)
                    .collect(Collectors.joining(", "));
            System.out.println(category + " — товари: " + products);
        }
    }

    public void showOrders() {
        for (Order order : repo.getOrders().getRows()) {
            System.out.println(order + ": клієнт — " + order.getClient().getFullName()
                    + ", відповідальний — " + order.getManager().getFullName());
        }
    }

    public void showOrdersByClient() {
        for (Client client : repo.getClients().getRows()) {
            System.out.println(client + " — замовлень: " + client.getOrders().size());
        }
    }
}
