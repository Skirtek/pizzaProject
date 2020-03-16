import pl.lab.enums.Pizza;
import pl.lab.services.IPizzaDataService;
import pl.lab.services.PizzaDataService;

public class Main {

    private static final IPizzaDataService pizzaDataService = new PizzaDataService();

    public static void main(String[] args) {
        Pizza[] pizzas = Pizza.values();

        System.out.println(pizzaDataService.groupByPrice(pizzas));
        System.out.println(pizzaDataService.formattedMenu(pizzas));
    }
}
