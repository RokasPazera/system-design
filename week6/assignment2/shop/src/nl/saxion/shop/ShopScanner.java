package nl.saxion.shop;

import java.util.ArrayList;

public class ShopScanner {
    ArrayList<Product> groceries = new ArrayList<>();


    public void addGrocery(String name){
        //TODO: find the product in the shop by name
        // if it does not exist: show error message
        // if found add it the grocerylist
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
