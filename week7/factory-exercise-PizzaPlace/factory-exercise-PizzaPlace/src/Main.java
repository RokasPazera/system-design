import net.saxion.pizzaplace.ApeldoornStore;
import net.saxion.pizzaplace.DeventerStore;
import net.saxion.pizzaplace.EnschedeStore;
import net.saxion.pizzaplace.Store;

public class Main {
    public static void main(String[] args) {

        System.out.println("The Pizza P(a)lace");
        Store deventerStore = new DeventerStore();
        Store enschedeStore = new EnschedeStore();
        Store apeldoornStore = new ApeldoornStore();
        try {
            deventerStore.orderPizza("cheese");
            System.out.println("=============== Next Order ===============");
            enschedeStore.orderPizza("greek");
            System.out.println("=============== Next Order ===============");
            apeldoornStore.orderPizza("hawaii");
            System.out.println("=============== Next Order ===============");
            deventerStore.orderPizza("greek");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
