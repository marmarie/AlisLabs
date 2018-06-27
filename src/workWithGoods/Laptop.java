package workWithGoods;

import java.time.LocalDate;

public class Laptop extends Product {
    public Laptop(String name, String manufacturer, double wholesalePrice, double retailPrice, LocalDate expiryDate, int quantity) {
        super(name, manufacturer, wholesalePrice, retailPrice, expiryDate, quantity);
    }

    @Override
    String printProduct() {
        return "Laptop " + nameProperty().getName() + " produced by " + manufacturerProperty().getName();
    }
}
