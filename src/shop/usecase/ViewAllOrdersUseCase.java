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
