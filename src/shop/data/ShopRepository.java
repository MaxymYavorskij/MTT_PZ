package shop.data;

import shop.model.Category;
import shop.model.Client;
import shop.model.Manager;
import shop.model.Order;
import shop.model.Product;
import shop.model.ProductInfo;

public class ShopRepository {
    private final Table<Product> products = new Table<>();
    private final Table<Category> categories = new Table<>();
    private final Table<ProductInfo> productInfos = new Table<>();
    private final Table<Client> clients = new Table<>();
    private final Table<Manager> managers = new Table<>();
    private final Table<Order> orders = new Table<>();

    public Table<Product> getProducts() { return products; }
    public Table<Category> getCategories() { return categories; }
    public Table<ProductInfo> getProductInfos() { return productInfos; }
    public Table<Client> getClients() { return clients; }
    public Table<Manager> getManagers() { return managers; }
    public Table<Order> getOrders() { return orders; }
}
