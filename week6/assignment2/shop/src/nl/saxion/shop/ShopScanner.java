package nl.saxion.shop;
import nl.saxion.app.SaxionApp;

import java.util.ArrayList;

public class ShopScanner {
    ArrayList<Product> groceries = new ArrayList<>();


    public void addGrocery(String name){
        Product product = Shop.getInstance().findProduct(name);
        if(product == null){
            SaxionApp.printLine("Product does not exist in the store.");
        } else {
            groceries.add(product);
        }
    }


    public String getOverview(){
        StringBuilder result = new StringBuilder();
        result.append("--------------------------------------------\n");
        result.append("-------------groceries scanned--------------\n");
        result.append("--------------------------------------------\n");
        for (Product grocery : groceries) {
            result.append("- " + grocery + "\n");
        }
        result.append("--------------------------------------------\n");
        return result.toString();
    }
}
