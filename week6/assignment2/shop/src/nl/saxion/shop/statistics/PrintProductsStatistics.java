package nl.saxion.shop.statistics;

public class PrintProductsStatistics {
    public void printStatistics(){
        SaxionApp.printLine("----------------products in the store----------------");
        for (Product product : Shop.getInstance().getProducts()) {
            SaxionApp.printLine("- " + product);
        }
    }
}
