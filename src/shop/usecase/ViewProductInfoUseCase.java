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
