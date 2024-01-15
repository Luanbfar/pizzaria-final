package br.com.pizzaria.entidades.acoes;

import br.com.pizzaria.entidades.Pedido;
import br.com.pizzaria.entidades.Pizza;

import java.util.*;

public class Metodo {
    Pizza pizza = new Pizza(null);
    private Queue<Pedido> pedidos = new ArrayDeque();
    private ArrayList<Pizza> pizzasCriadas = new ArrayList<>();

    private ArrayList<Pizza> pizzasServidas = new ArrayList<>();

    public ArrayList<Pizza> getPizzasCriadas() {
        return pizzasCriadas;
    }

    public Queue<Pedido> getPedido() {
        return pedidos;
    }

    public void prepararPizza(ArrayList<String> ingredientes) {
        if (ingredientes != null && ingredientes.size() == 5) {
            Pizza novaPizza = new Pizza(ingredientes);
            pizzasCriadas.add(novaPizza);
        }
    }

    public Boolean servirPedido() {
        Boolean pedidoServido = false;
        Pizza pizzaAchada = null;
        ArrayList<String> ingredientesDoPedido = pedidos.peek().getIngredientesAleatorios();
        if (!pedidos.isEmpty()) {
            for (Pizza p : pizzasCriadas) {
                int ingredientesCorretos = 0;
                for (String ingredientePizza : ingredientesDoPedido) {
                    if (p.getPizzas().contains(ingredientePizza)) {
                        ingredientesCorretos++;
                    }
                }
                if (ingredientesCorretos >= 3) {
                    pizzaAchada = p;
                    pedidos.poll();
                    pedidoServido = true;
                    break;
                }
            }
        }
        if (pizzaAchada != null) {
            pizzasServidas.add(pizzaAchada);
            pizzasCriadas.remove(pizzaAchada);
        }
        return pedidoServido;
    }


    public String olharPedidoAtual() {
        StringBuilder pedidoAtualStr = new StringBuilder();
        if (!pedidos.isEmpty()) {
            Pedido pedidoAtual = pedidos.peek();
            pedidoAtualStr.append("Pedido Atual:\n");
            pedidoAtualStr.append("ID: ").append(pedidoAtual.getId()).append("\n");
            pedidoAtualStr.append("Nome: ").append(pedidoAtual.getNomeCliente()).append("\n");
            pedidoAtualStr.append("Ingredientes: ").append(pedidoAtual.getIngredientesAleatorios()).append("\n");
            pedidoAtualStr.append("=========================\n");
        }
        return pedidoAtualStr.toString();
    }

    public void receberUmPedidoAleatorio(String nome) {
        Random pedidoRandomico = new Random();
        if (nome != null) {
            ArrayList<String> ingredientesAleatorios = new ArrayList<>();
            Pedido pedido;
            for (int i = 0; i < 5; i++) {
                int indiceIngrediente = pedidoRandomico.nextInt(pizza.getIngredientes().size());
                String ingrediente = pizza.getIngredientes().get(indiceIngrediente);
                ingredientesAleatorios.add(ingrediente);
            }
            pedido = new Pedido(nome, ingredientesAleatorios);
            pedidos.add(pedido);
        }
    }

    public String calcularEstatisticas() {
        StringBuilder estatisticas = new StringBuilder();

        int totalPizzasServidas = pizzasServidas.size();
        estatisticas.append("Pizzas servidas: ").append(totalPizzasServidas).append("\n");

        if (totalPizzasServidas > 0) {
            HashMap<String, Integer> ingredientesUtilizados = new HashMap<>();
            for (Pizza pizza : pizzasServidas) {
                for (String ingrediente : pizza.getIngredientes()) {
                    ingredientesUtilizados.put(ingrediente, ingredientesUtilizados.getOrDefault(ingrediente, 0) + 1);
                }
            }

            String ingredienteMaisPedido = "";
            int vezesMaisPedido = 0;
            if (!ingredientesUtilizados.isEmpty()) {
                for (Map.Entry<String, Integer> entry : ingredientesUtilizados.entrySet()) {
                    if (entry.getValue() > vezesMaisPedido) {
                        ingredienteMaisPedido = entry.getKey();
                        vezesMaisPedido = entry.getValue();
                    }
                }
                estatisticas.append("Ingrediente mais pedido: ").append(ingredienteMaisPedido).append("\n");
            }

            ArrayList<String> ingredientesNaoUtilizados = new ArrayList<>();
            ArrayList<String> ingredientesDisponiveis = pizza.getIngredientes();
            for (String ingrediente : ingredientesDisponiveis) {
                if (!ingredientesUtilizados.containsKey(ingrediente)) {
                    ingredientesNaoUtilizados.add(ingrediente);
                }
            }
            estatisticas.append("Ingredientes não utilizados: ").append(ingredientesNaoUtilizados).append("\n");

            int totalIngredientesCorretos = 0;
            for (Pizza pizza : pizzasServidas) {
                if (pizza.getIngredientes().size() == 5) {
                    totalIngredientesCorretos += 5;
                }
            }
            double quantidadeMediaIngredientesCorretos = (double) totalIngredientesCorretos / totalPizzasServidas;
            estatisticas.append("Quantidade média de ingredientes corretos por pizza: ").append(quantidadeMediaIngredientesCorretos).append("\n");
        } else {
            estatisticas.append("Nenhuma pizza servida ainda.\n");
            estatisticas.append("Ingrediente mais pedido: N/A\n");
            estatisticas.append("Ingredientes não utilizados: N/A\n");
            estatisticas.append("Quantidade média de ingredientes corretos por pizza: N/A\n");
        }

        int pedidosNaFila = pedidos.size();
        estatisticas.append("Pedidos na fila: ").append(pedidosNaFila).append("\n");

        return estatisticas.toString();
    }

}
