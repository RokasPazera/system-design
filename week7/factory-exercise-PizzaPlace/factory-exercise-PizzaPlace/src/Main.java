import net.saxion.pizzaplace.Store;

public class Main {
    public static void main(String[] args) {

        System.out.println("The Pizza P(a)lace");
        Store pizzaStore = new Store();
        try {
            pizzaStore.orderPizza("cheese");
            System.out.println("=============== Next Order ===============");
            pizzaStore.orderPizza("pepperoni");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}