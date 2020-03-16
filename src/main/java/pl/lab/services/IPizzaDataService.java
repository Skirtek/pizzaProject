package pl.lab.services;

import pl.lab.enums.Pizza;

import java.util.List;
import java.util.Map;

public interface IPizzaDataService {

    /**
     * Method to find the cheapest pizza which has at least one spicy ingredient
     *
     * @param pizzas - collection of objects which represents pizzas to search
     * @return - object which represents pizza or null if collection does no contain any matching pizza
     */
    Pizza findCheapestSpicy(Pizza[] pizzas);

    /**
     * Method to find the most expensive pizza which has not any meat ingredient
     *
     * @param pizzas - collection of objects which represents pizzas to search
     * @return - object which represents pizza or null if collection does no contain any matching pizza
     */
    Pizza findMostExpensiveVegetarian(Pizza[] pizzas);

    /**
     * Method to get collections of pizzas which has at least one meat ingredient
     *
     * @param pizzas - collection of objects which represents pizzas to search
     * @return - collection with object which represents pizza with meat.
     * Collection is ordered by number of meat ingredient, descending.
     */
    List<Pizza> iLikeMeat(Pizza[] pizzas);

    /**
     * Method to get pizzas grouped by price
     *
     * @param pizzas - collection of objects which represents pizzas to search
     * @return - map where key is price of pizza and value is collection of pizzas which price equals to key price
     */
    Map<Integer, List<Pizza>> groupByPrice(Pizza[] pizzas);

    /**
     * Method to get formatted menu
     *
     * @param pizzas - collection of objects which represents pizzas to search
     * @return - string representation of menu.
     * Each pizza has pattern - name: ingredients separated by comma - price.
     * Pizzas are separated by new line character
     */
    String formattedMenu(Pizza[] pizzas);
}