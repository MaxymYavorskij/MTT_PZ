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
