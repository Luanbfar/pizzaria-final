package br.com.pizzaria.entidades.acoes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

import br.com.pizzaria.entidades.Pizza;

public class Metodo {
	Pizza pizz = new Pizza(null, null);
	private Queue<Pizza> pedidos = new ArrayDeque();
	private ArrayList<Pizza> pizzas = new ArrayList<>();

	public ArrayList<Pizza> getPizzas() {
		return pizzas;
	}

	public Queue<Pizza> getPedido() {
		return pedidos;
	}


	public String retornarIngrediente() { // Cardapio
		String ingrediente = " ";
		ingrediente = "=========================";
		for (String i : pizz.getIngredientes()) {
			ingrediente = i + "\n";
		}
		ingrediente = "=========================";
		return ingrediente;
	}

	public void prepararPizza(Pizza pizza) throws IngredienteInsuficiente {
		// Lembrar do Contains no menu pra escolher os ingredientes QUE EXISTEM NO
								// CARDAPIO!
		if (pizza != null && pizza.getIngredientesAdicional().size() == 5) {
			pizzas.add(pizza);
		} else {
			throw new IngredienteInsuficiente();
		}
	}

	public void servirPedido() {
		if (!pedidos.isEmpty()) {
			Pizza pizzaAServir = pedidos.poll(); // Obtém e remove a primeira pizza da fila
			pizzas.removeIf(pizza -> pizza.getId() == pizzaAServir.getId());
		}
	}

	public String olharPedidoAtual() throws NaoExistemPedidos {
	    if (!pedidos.isEmpty()) {  //PRA N RETORNAR NULL O PEEK
	        Pizza pedidoAtual = pedidos.peek();   //lembrar q o peek pega o primeiro sem remover!!! É APENAS O POOL!! 
	        StringBuilder pedidoAtualStr = new StringBuilder();
	        pedidoAtualStr.append("Pedido Atual:\n");
	        pedidoAtualStr.append("ID: ").append(pedidoAtual.getId()).append("\n");
	        pedidoAtualStr.append("Nome: ").append(pedidoAtual.getNomeCliente()).append("\n");
	        pedidoAtualStr.append("Ingredientes: ").append(pedidoAtual.getIngredientesAdicional()).append("\n");
	        pedidoAtualStr.append("=========================\n");

	        return pedidoAtualStr.toString();
	    } else {
	        throw new NaoExistemPedidos();
	    }
	}


	public String todosOsPedidos() throws NaoExistemPedidos {    ///LEMBRAR QUE ESSE METODO NAO É NECESSARIO! SÓ P VER SE TEM PEDIDO MSM
		if (!pedidos.isEmpty()) {
			StringBuilder pedidosAtuais = new StringBuilder();

			for (Pizza pedidoAtual : pedidos) {
				pedidosAtuais.append("Pedido Atual:\n");
				pedidosAtuais.append("ID: ").append(pedidoAtual.getId()).append("\n");
				pedidosAtuais.append("Nome: ").append(pedidoAtual.getNomeCliente()).append("\n");
				pedidosAtuais.append("Ingredientes: ").append(pedidoAtual.getIngredientesAdicional()).append("\n");
				pedidosAtuais.append("=========================\n");
			}

			return pedidosAtuais.toString();
		} else {
			throw new NaoExistemPedidos();
		}
	}
	
	public String listarPizzaCriada() {                //LEMBRAR Q ESSE METODO NAO É NECESSÁRIO!!! SÓ P VER SE TEM PIZZA CRIADA
		String pizzaAchada = null;
		for (Pizza p : pizzas) {
			pizzaAchada += "=========================";
			pizzaAchada += "\nID: " + p.getId();
			pizzaAchada += "\nIngredietes: " + p.getIngredientesAdicional();
			pizzaAchada += "\n=========================";
		}
		return pizzaAchada;
	}

	public void receberUmPedidoAleatorio(String nome) {
		Random pedidoRandomico = new Random();
		if (nome != null) {
			ArrayList<String> ingredientesAleatorios = new ArrayList<>();
			Pizza pedido = null;
			for (int i = 0; i < 5; i++) {
				int indiceIngrediente = pedidoRandomico.nextInt(pizz.getIngredientes().size());
				String ingrediente = pizz.getIngredientes().get(indiceIngrediente);
				ingredientesAleatorios.add(ingrediente);
				pedido = new Pizza(nome, ingredientesAleatorios);
			}
			pedidos.add(pedido);

			// prepararPizza(new Pizza(nome, ingredientesAleatorios)); não deve ter, é
			// apenas um pedido!
		} else {
		}
	}

}