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
