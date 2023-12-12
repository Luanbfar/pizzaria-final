package br.com.pizzaria.entidades;

import java.util.ArrayList;

public class Pizza {
	private int id;
	private String nomeCliente;
	private ArrayList<String> ingredientesAdicional;
	private static int cont = 0;

	public Pizza(String nomeCliente, ArrayList<String> ingredientesAdicional) {
		this.nomeCliente = nomeCliente;
		this.ingredientesAdicional = ingredientesAdicional;
		this.id = cont++;

	}

	public Pizza(ArrayList<String> ingredientesAdicional) {
		this.ingredientesAdicional = ingredientesAdicional;
		this.id = cont++;
	}

	public final ArrayList<String> getIngredientes() {
		ArrayList<String> ingredientes = new ArrayList<>();
		ingredientes.add("Calabresaa");
		ingredientes.add("Carne");
		ingredientes.add("Mussarela");
		ingredientes.add("Chedar");
		ingredientes.add("Molho de Tomate");
		ingredientes.add("Batata Palha");
		ingredientes.add("Ovo");
		ingredientes.add("Azeitona");
		ingredientes.add("Milho");
		ingredientes.add("Qualho");
		return ingredientes;
	}

	// Na classe Pizza
	public String ingredientesSTR() {
		StringBuilder allIngredientesDisponiveis = new StringBuilder("Ingredientes disponíveis:\n");

		ArrayList<String> ingredientesDisponiveis = getIngredientes();

		if (ingredientesDisponiveis != null && !ingredientesDisponiveis.isEmpty()) {
			for (String ingrediente : ingredientesDisponiveis) {
				allIngredientesDisponiveis.append("==========================").append("\n");
				allIngredientesDisponiveis.append(ingrediente).append("\n");
			}

		}
		return allIngredientesDisponiveis.toString();
	}

	public int getId() {
		return id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public ArrayList<String> getIngredientesAdicional() {
		return ingredientesAdicional;
	}
}