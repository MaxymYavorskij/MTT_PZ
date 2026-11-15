package shop;

import shop.model.Order;
import shop.model.OrderState;
import shop.model.Product;

/**
 * Бізнес-правила системи з Додаткової специфікації (BR-1 ... BR-5).
 */
public final class BusinessRules {

    private BusinessRules() {}

    /** BR-1: сума замовлення — це сума цін усіх товарів у ньому. */
    public static double calculateOrderTotal(Order order) {
        return order.getItems().stream()
                .mapToDouble(Product::getCost)
                .sum();
    }

    /** BR-2: замовлення можна опрацювати лише один раз (тільки зі стану NEW). */
    public static boolean canProcessOrder(Order order) {
        return order != null && order.getState() == OrderState.NEW;
    }

    // BR-5: товар може належати до кількох категорій. Окрема перевірка не потрібна:
    // у Product для цього вже є список categories, а addCategory не додає дублікатів.
}