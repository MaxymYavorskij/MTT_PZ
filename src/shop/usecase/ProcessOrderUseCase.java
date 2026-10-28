package shop.usecase;

import shop.BusinessRules;
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

        // BR-2: замовлення можна опрацювати лише один раз
        if (!BusinessRules.canProcessOrder(order)) {
            System.out.println(order + " вже опрацьовано: " + order.getManager().getFullName());
            return;
        }

        order.assignManager(manager);
        System.out.println(order + " опрацьовує " + manager.getFullName());
    }
}
