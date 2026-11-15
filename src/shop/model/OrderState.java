package shop.model;

// Стани замовлення згідно з UML діаграмою станів (Лаб. 5)
public enum OrderState {
    NEW("Нове"),
    PROCESSING("Опрацьовується"),
    SHIPPED("Відправлене"),
    COMPLETED("Виконане"),
    CANCELLED("Скасоване");

    private final String title;

    OrderState(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }

    // фінальні стани: з них переходів немає
    public boolean isFinal() {
        return this == COMPLETED || this == CANCELLED;
    }
}
