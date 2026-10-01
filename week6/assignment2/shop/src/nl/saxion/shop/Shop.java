package nl.saxion.shop;

import nl.saxion.app.CsvReader;
import nl.saxion.shop.statistics.StatisticsManager;

import java.util.ArrayList;

public class Shop {
    ArrayList<Product> products = new ArrayList<Product>();
    StatisticsManager statisticsManager;
    private static Shop instance;

    private Shop() {
        //setup
        statisticsManager = new StatisticsManager();


        loadProductData();
    }

    public void printProductsStatistics(){
        statisticsManager.printProductStatistics();
    }

    private void loadProductData(){
        CsvReader reader = new CsvReader("products.csv");
        reader.setSeparator(';');
        reader.skipRow();
        while(reader.loadRow()){
            Product product = new Product(reader.getString(0), reader.getDouble(1));
            products.add(product);
        }
    }

    public static Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
        }
        return instance;
    }

    public Product findProduct(String name){
        for (Product product : products){
            if(product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

}
