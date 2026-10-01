package nl.saxion.shop.statistics;

public class StatisticsManager {

    PrintProductsStatistics productsStatistics;

    //note: later we might add more statistic classes here
    // ...
    // ...


    public StatisticsManager() {
        productsStatistics = new PrintProductsStatistics();
    }




    public void printProductStatistics(){
        productsStatistics.printStatistics();
    }
}
