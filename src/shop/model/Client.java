package shop.model;

// Клієнт: оформлює замовлення (orders — замовлення, які він оформив)
public class Client extends Person {

    public Client(String fullName) {
        super(fullName);
    }

    @Override
    public String getRole() { return "Клієнт"; }
}
