package pl.lab.services;

import pl.lab.enums.Ingredient;
import pl.lab.enums.Pizza;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PizzaDataService implements IPizzaDataService {

    @Override
    public Pizza findCheapestSpicy(Pizza[] pizzas) {
        Optional<Pizza> result = getPizzasStream(pizzas)
                .filter(x -> x.getIngredients().stream().anyMatch(Ingredient::isSpicy))
                .min(Comparator.comparing(this::getPrice));

        return result.orElse(null);
    }

    @Override
    public Pizza findMostExpensiveVegetarian(Pizza[] pizzas) {
        Optional<Pizza> result = getPizzasStream(pizzas)
                .filter(x -> x.getIngredients().stream().noneMatch(Ingredient::isMeat))
                .max(Comparator.comparing(this::getPrice));

        return result.orElse(null);
    }

    @Override
    public List<Pizza> iLikeMeat(Pizza[] pizzas) {
        return getPizzasStream(pizzas)
                .filter(x -> x.getIngredients().stream().anyMatch(Ingredient::isMeat))
                .sorted(Comparator.comparing(this::countMeatIngredients).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Pizza>> groupByPrice(Pizza[] pizzas) {
        return getPizzasStream(pizzas).collect(Collectors.groupingBy(this::getPrice));
    }

    @Override
    public String formattedMenu(Pizza[] pizzas) {
        return getPizzasStream(pizzas)
                .map(pizza -> pizza.getName() + ": " + getIngredients(pizza) + " - cena: " + getPrice(pizza))
                .collect(Collectors.joining("\n"));
    }

    private String getIngredients(Pizza pizza) {
        return pizza.getIngredients().stream().map(Ingredient::getName).collect(Collectors.joining(", "));
    }

    private int getPrice(Pizza pizza) {
        return pizza.getIngredients().stream().mapToInt(Ingredient::getPrice).sum();
    }

    private Stream<Pizza> getPizzasStream(Pizza[] pizzas) {
        return Arrays.stream(pizzas);
    }

    private long countMeatIngredients(Pizza pizza) {
        return pizza.getIngredients().stream().filter(Ingredient::isMeat).count();
    }
}