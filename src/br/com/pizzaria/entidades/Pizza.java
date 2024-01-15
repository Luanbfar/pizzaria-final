package br.com.pizzaria.entidades;

import java.util.ArrayList;

public class Pizza {
    private ArrayList<String> pizzas;
    private static final ArrayList<String> ingredientes = new ArrayList<>();

    public Pizza(ArrayList<String> pizzas) {
        this.pizzas = pizzas;
    }
    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }
    public static final void addIngredientes() {
        ingredientes.add("Calabresa");
        ingredientes.add("Carne");
        ingredientes.add("Mussarela");
        ingredientes.add("Cheddar");
        ingredientes.add("Molho de Tomate");
        ingredientes.add("Batata Palha");
        ingredientes.add("Ovo");
        ingredientes.add("Azeitona");
        ingredientes.add("Milho");
        ingredientes.add("Qualho");
    }

    public String ingredientesStr() {
        StringBuilder allIngredientesDisponiveis = new StringBuilder("Ingredientes disponíveis:\n");

        ArrayList<String> ingredientesDisponiveis = getIngredientes();

        if (!ingredientesDisponiveis.isEmpty()) {
            for (String ingrediente : ingredientesDisponiveis) {
                allIngredientesDisponiveis.append("==========================").append("\n");
                allIngredientesDisponiveis.append(ingrediente).append("\n");
            }

        }
        return allIngredientesDisponiveis.toString();
    }

    public ArrayList<String> getPizzas() {
        return pizzas;
    }

}
