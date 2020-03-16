import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lab.enums.Pizza;
import pl.lab.services.IPizzaDataService;
import pl.lab.services.PizzaDataService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PizzaDataServiceTests {

    private IPizzaDataService service;

    @Before
    public void setUp() {
        service = new PizzaDataService();
    }

    @Test
    public void findCheapestSpicy_NoMatchingPizzas_ReturnsNull() {
        // arrange
        Pizza[] pizzas = {Pizza.MARGHERITA};

        // act
        Pizza result = service.findCheapestSpicy(pizzas);

        // assert
        Assert.assertNull(result);
    }

    @Test
    public void findCheapestSpicy_OneMatchingPizza_ReturnsValidPizza() {
        // arrange
        Pizza expectedPizza = Pizza.TABASCO;
        Pizza[] pizzas = {expectedPizza, Pizza.MARGHERITA};

        // act
        Pizza result = service.findCheapestSpicy(pizzas);

        // assert
        Assert.assertNotNull(result);
        Assert.assertEquals(expectedPizza, result);
    }

    @Test
    public void findCheapestSpicy_MultipleMatchingPizzas_ReturnsValidPizza() {
        // arrange
        Pizza expectedPizza = Pizza.CARUSO;
        Pizza[] pizzas = {expectedPizza, Pizza.TABASCO, Pizza.MARGHERITA};

        // act
        Pizza result = service.findCheapestSpicy(pizzas);

        // assert
        Assert.assertNotNull(result);
        Assert.assertEquals(expectedPizza, result);
    }

    @Test
    public void findMostExpensiveVegetarian_NoMatchingPizzas_ReturnsNull() {
        // arrange
        Pizza[] pizzas = {Pizza.CALABRESE};

        // act
        Pizza result = service.findMostExpensiveVegetarian(pizzas);

        // assert
        Assert.assertNull(result);
    }

    @Test
    public void findMostExpensiveVegetarian_OneMatchingPizza_ReturnsValidPizza() {
        // arrange
        Pizza expectedPizza = Pizza.MARGHERITA;
        Pizza[] pizzas = {expectedPizza, Pizza.CALABRESE};

        // act
        Pizza result = service.findMostExpensiveVegetarian(pizzas);

        // assert
        Assert.assertNotNull(result);
        Assert.assertEquals(expectedPizza, result);
    }

    @Test
    public void findMostExpensiveVegetarian_MultipleMatchingPizzas_ReturnsValidPizza() {
        // arrange
        Pizza expectedPizza = Pizza.VEGETARIANA;
        Pizza[] pizzas = {expectedPizza, Pizza.MARGHERITA, Pizza.CALABRESE};

        // act
        Pizza result = service.findMostExpensiveVegetarian(pizzas);

        // assert
        Assert.assertNotNull(result);
        Assert.assertEquals(expectedPizza, result);
    }

    @Test
    public void iLikeMeat_NoMatchingPizzas_ReturnsEmptyList() {
        // arrange
        Pizza[] pizzas = {Pizza.VEGETARIANA};

        // act
        List<Pizza> result = service.iLikeMeat(pizzas);

        // assert
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void iLikeMeat_MultipleMatchingPizzas_ReturnsPizzasInValidOrder() {
        // arrange
        List<Pizza> expectedResult = Arrays.asList(Pizza.CALABRESE, Pizza.CARUSO);
        Pizza[] pizzas = {Pizza.VEGETARIANA, Pizza.CARUSO, Pizza.CALABRESE};

        // act
        List<Pizza> result = service.iLikeMeat(pizzas);

        // assert
        Assert.assertNotNull(result);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void groupByPrice_NoPizzas_ReturnsEmptyMap() {
        // arrange
        Pizza[] pizzas = {};

        // act
        Map<Integer, List<Pizza>> result = service.groupByPrice(pizzas);

        // assert
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void groupByPrice_TwoDifferentPizzas_ReturnsMapWithTwoEntries() {
        // arrange
        Pizza[] pizzas = {Pizza.VEGETARIANA, Pizza.FARMER};

        // act
        Map<Integer, List<Pizza>> result = service.groupByPrice(pizzas);

        // assert
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(2, result.keySet().size());
    }

    @Test
    public void groupByPrice_TwoGroupOfPizzas_ReturnsMapWithTwoKeys() {
        // arrange
        List<Pizza> expectedResult = Arrays.asList(Pizza.PASCETORE, Pizza.AMORE);
        Pizza[] pizzas = {Pizza.PASCETORE, Pizza.HAVAI, Pizza.CAPRI, Pizza.AMORE};

        // act
        Map<Integer, List<Pizza>> result = service.groupByPrice(pizzas);

        // assert
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(2, result.keySet().size());
        Assert.assertNotNull(result.get(16));
        Assert.assertEquals(expectedResult, result.get(16));
    }

    @Test
    public void formattedMenu_NoPizzas_ReturnsEmptyString() {
        // arrange
        Pizza[] pizzas = {};

        // act
        String result = service.formattedMenu(pizzas);

        // assert
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void formattedMenu_OnePizza_ReturnsValidString() {
        // arrange
        Pizza[] pizzas = {Pizza.VEGETARIANA};

        // act
        String result = service.formattedMenu(pizzas);

        // assert
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals("Vegetariana: cienkie ciasto, sos pomidorowy, ser, cebula, fasola, kukurydza, brokuły, rukola - cena: 20", result);
    }

    @Test
    public void formattedMenu_MultiplePizzas_ReturnsValidString() {
        // arrange
        Pizza[] pizzas = {Pizza.VEGETARIANA, Pizza.FARMER};

        // act
        String result = service.formattedMenu(pizzas);

        // assert
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals("Vegetariana: cienkie ciasto, sos pomidorowy, ser, cebula, fasola, kukurydza, brokuły, rukola - cena: 20\n" +
                "Farmerska: grube ciasto, sos pomidorowy, ser, kurczak, bekon, cebula, kukurydza - cena: 22", result);
    }
}
