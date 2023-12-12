package br.com.pizzaria.entidades.acoes;

public class IngredienteInsuficiente extends Exception {
	public IngredienteInsuficiente() {
		super("Coloque 5 Ingredientes!!");
	}

}
