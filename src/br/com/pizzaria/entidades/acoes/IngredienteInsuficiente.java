package br.com.pizzaria.entidades.acoes;

public class IngredienteInsuficiente extends Exception {
	public IngredienteInsuficiente() {
		super("\nColoque 5 Ingredientes!!");
	}

}
