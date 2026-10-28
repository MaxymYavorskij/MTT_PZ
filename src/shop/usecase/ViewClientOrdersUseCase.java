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
