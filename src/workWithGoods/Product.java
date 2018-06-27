package workWithGoods;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.Date;

public abstract class Product {




    private SimpleStringProperty name;
    private SimpleStringProperty manufacturer;
    private SimpleIntegerProperty quantity;
    private SimpleDoubleProperty wholesalePrice;
    private SimpleDoubleProperty retailPrice;
    private SimpleStringProperty expiryDate;



    public Product(String name, String manufacturer, double wholesalePrice, double retailPrice, LocalDate expiryDate, int quantity) {
        this.name = new SimpleStringProperty(name);
        this.manufacturer = new SimpleStringProperty(manufacturer);
        this.wholesalePrice = new SimpleDoubleProperty(wholesalePrice);
        this.retailPrice = new SimpleDoubleProperty(retailPrice);
        this.expiryDate = new SimpleStringProperty(expiryDate.toString());
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getManufacturer() {
        return manufacturer.get();
    }

    public SimpleStringProperty manufacturerProperty() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer.set(manufacturer);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public double getWholesalePrice() {
        return wholesalePrice.get();
    }

    public SimpleDoubleProperty wholesalePriceProperty() {
        return wholesalePrice;
    }

    public void setWholesalePrice(double wholesalePrice) {
        this.wholesalePrice.set(wholesalePrice);
    }

    public double getRetailPrice() {
        return retailPrice.get();
    }

    public SimpleDoubleProperty retailPriceProperty() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice.set(retailPrice);
    }

    public LocalDate getExpiryDate() {
        return LocalDate.parse(expiryDate.get());
    }

    public SimpleStringProperty expiryDateProperty() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate.set(expiryDate.toString());
    }

    abstract String printProduct();


}
