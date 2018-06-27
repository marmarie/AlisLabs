package workWithGoods;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class SetOfProducts {

  private ArrayList<Product> products;

   public ArrayList<Product> getProductsByManufacturer(String manufacturer) {
       ArrayList<Product> productsByManufacturer = new ArrayList<>();
       for (Product product : products){
           if (product.getManufacturer().equalsIgnoreCase(manufacturer))
               productsByManufacturer.add(product);
       }
       return productsByManufacturer;
   }

    public ArrayList<Product> getProductsByName(String name) {
        ArrayList<Product> productsByName= new ArrayList<>();
        for (Product product : products){
            if (product.getName().equalsIgnoreCase(name))
                productsByName.add(product);
        }
        return productsByName;
    }


    public ArrayList<Product> getProductsByThePrice(double wholesalePrice) {
        ArrayList<Product> productsByThePrice= new ArrayList<>();
        for (Product product : products){
            if (product.getWholesalePrice() == wholesalePrice)
                productsByThePrice.add(product);
        }
        return productsByThePrice;
    }

    public Product getProductWithTheHighestPrice(){
       double maxPrice = 0;
       Product maxPriceProduct= null;

        for (Product product : products){
            if (product.getWholesalePrice() > maxPrice ) {
              //  maxPrice = product.getWholesalePrice();
                maxPriceProduct = product;
            }

        }
        return maxPriceProduct;

    }

    private boolean checkValueNotEmpty(Object value){
       return !value.equals(0) || !value.equals("") || !value.equals(null);
    }

    public void editProductByIndex(int index, String name, String manufacturer, double wholesalePrice, double retailPrice, LocalDate expiryDate, int quantity){
        if(index>=0 && index <products.size()) {
            Product product = products.get(index);
            if (checkValueNotEmpty(name))
                product.setName(name);
            if (checkValueNotEmpty(manufacturer))
                product.setManufacturer(manufacturer);
            if(checkValueNotEmpty(wholesalePrice))
                product.setWholesalePrice(wholesalePrice);
            if(checkValueNotEmpty(retailPrice))
                product.setRetailPrice(retailPrice);
            if(checkValueNotEmpty(expiryDate))
                product.setExpiryDate(expiryDate);
        } else throw new IndexOutOfBoundsException("Please, provide the correct index!");


    }




    public void printProducts(){
        for (Product product : products)
            product.printProduct();
    }

    public boolean addProduct(Product product){
     return products.add(product);
    }





}
