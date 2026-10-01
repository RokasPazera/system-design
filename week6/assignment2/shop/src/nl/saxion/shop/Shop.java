package nl.saxion.shop;

import nl.saxion.app.CsvReader;
import nl.saxion.shop.statistics.StatisticsManager;

import java.util.ArrayList;

public class Shop {
    ArrayList<Product> products = new ArrayList<Product>();
    StatisticsManager statisticsManager;

    public Shop() {
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
}
