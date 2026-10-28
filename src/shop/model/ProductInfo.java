package shop.model;

public class ProductInfo implements Entity {
    private int id;
    private String text;

    private final Product product;

    public ProductInfo(String text, Product product) {
        this.text = text;
        this.product = product;
        product.setInfo(this);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Product getProduct() { return product; }

    @Override
    public String toString() {
        return "[" + id + "] " + text;
    }
}
