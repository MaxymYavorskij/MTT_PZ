package shop;

import shop.data.ShopRepository;
import shop.model.Category;
import shop.model.Client;
import shop.model.Manager;
import shop.model.Product;
import shop.model.ProductInfo;
import shop.usecase.PlaceOrderUseCase;
import shop.usecase.ProcessOrderUseCase;
import shop.usecase.ViewAllOrdersUseCase;
import shop.usecase.ViewCatalogUseCase;
import shop.usecase.ViewClientOrdersUseCase;
import shop.usecase.ViewProductInfoUseCase;
import shop.usecase.ViewProductsByCategoryUseCase;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        ShopRepository repo = new ShopRepository();

        Product headphones = new Product("Навушники", 1200);
        Product phoneCase = new Product("Чохол для телефону", 300);
        Product powerbank = new Product("Powerbank", 900);
        repo.getProducts().add(headphones);
        repo.getProducts().add(phoneCase);
        repo.getProducts().add(powerbank);

        Category electronics = new Category("Електроніка");
        Category accessories = new Category("Аксесуари");
        Category promo = new Category("Акційний товар");
        repo.getCategories().add(electronics);
        repo.getCategories().add(accessories);
        repo.getCategories().add(promo);

        headphones.addCategory(electronics);
        headphones.addCategory(accessories);
        phoneCase.addCategory(accessories);
        phoneCase.addCategory(promo);
        powerbank.addCategory(electronics);

        repo.getProductInfos().add(new ProductInfo("бездротові навушники з шумозаглушенням", headphones));
        repo.getProductInfos().add(new ProductInfo("силіконовий захисний чохол з відділенням для картки", phoneCase));
        repo.getProductInfos().add(new ProductInfo("портативний акумулятор ємністю 10000 мА·год", powerbank));

        repo.getClients().add(new Client("Мельник Богдан"));
        repo.getClients().add(new Client("Гриценко Оксана"));

        repo.getManagers().add(new Manager("Савчук Дмитро"));
        repo.getManagers().add(new Manager("Бондар Наталія"));

        ViewCatalogUseCase viewCatalog = new ViewCatalogUseCase(repo);
        ViewProductsByCategoryUseCase viewByCategory = new ViewProductsByCategoryUseCase(repo);
        ViewProductInfoUseCase viewProductInfo = new ViewProductInfoUseCase(repo);
        PlaceOrderUseCase placeOrder = new PlaceOrderUseCase(repo, viewCatalog);
        ViewClientOrdersUseCase viewClientOrders = new ViewClientOrdersUseCase(repo);
        ViewAllOrdersUseCase viewAllOrders = new ViewAllOrdersUseCase(repo);
        ProcessOrderUseCase processOrder = new ProcessOrderUseCase(repo, viewAllOrders);

        System.out.println("=== Актор: Клієнт ===");

        System.out.println("\n--- ПР-1. Переглянути каталог товарів ---");
        viewCatalog.execute();

        System.out.println("\n--- ПР-2. Переглянути товари за категорією ---");
        viewByCategory.execute(2);
        viewByCategory.execute(9);

        System.out.println("\n--- ПР-3. Переглянути опис товару ---");
        viewProductInfo.execute(1);
        viewProductInfo.execute(9);

        System.out.println("\n--- ПР-4. Оформити замовлення ---");
        placeOrder.execute(1, 1, 3);
        System.out.println();
        placeOrder.execute(2, 2);
        System.out.println();
        placeOrder.execute(1, 9);

        System.out.println("\n--- ПР-5. Переглянути свої замовлення ---");
        viewClientOrders.execute(1);
        viewClientOrders.execute(2);

        System.out.println("\n=== Актор: Відповідальна особа ===");

        System.out.println("\n--- ПР-7. Переглянути всі замовлення ---");
        viewAllOrders.execute();

        System.out.println("\n--- ПР-6. Опрацювати замовлення ---");
        processOrder.execute(2, 1);
        System.out.println();
        processOrder.execute(1, 1);

        System.out.println("\n--- ПР-5. Клієнт перевіряє статус замовлень ---");
        viewClientOrders.execute(1);
    }
}