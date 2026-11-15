package shop.model;

// Відповідальна особа: опрацьовує замовлення (orders — замовлення, закріплені за нею)
public class Manager extends Person {

    public Manager(String fullName) {
        super(fullName);
    }

    @Override
    public String getRole() { return "Відповідальна особа"; }
}
