package nl.saxion.shop.statistics;

import nl.saxion.app.SaxionApp;
import nl.saxion.shop.Product;
import nl.saxion.shop.Shop;

public class PrintProductsStatistics {
    public void printStatistics(){
        SaxionApp.printLine("----------------products in the store----------------");
        for (Product product : Shop.getInstance().getProducts()) {
            SaxionApp.printLine("- " + product);
        }
    }
}
