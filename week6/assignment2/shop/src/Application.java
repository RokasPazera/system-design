import nl.saxion.app.SaxionApp;
import nl.saxion.shop.Shop;
import nl.saxion.shop.ShopScanner;


public class Application implements Runnable{


    Shop shop;


    public static void main(String[] args) {
        SaxionApp.start(new Application(), 700, 700);
    }


    @Override
    public void run() {
        //setup
        shop = new Shop();
        ShopScanner shopScanner = new ShopScanner(); //the scanner the user is holding

        boolean isRunning = true;
        while(isRunning){
            SaxionApp.printLine("1. Add grocery");
            SaxionApp.printLine("2. Show overview of all products in store");
            SaxionApp.printLine("3. Show overview of scanned groceries");
            SaxionApp.printLine("4. Exit");
            int input = SaxionApp.readInt();
            if(input == 1){
                SaxionApp.print("Name of product: ");
                String productName = SaxionApp.readString();
                shopScanner.addGrocery(productName);
            }else if(input == 2){
                shop.printProductsStatistics();
            }else if(input == 3){
                SaxionApp.printLine(shopScanner.getOverview());
            }else if(input == 4){
                isRunning = false;
            }
        }
    }





}
