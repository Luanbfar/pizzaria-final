package br.com.pizzaria.entidades.acoes;

import br.com.pizzaria.entidades.Pizza;

import java.util.*;

public class Metodo {
    Pizza pizza = new Pizza();
    private Queue<Pizza> pedidos = new ArrayDeque();
    private ArrayList<Pizza> pizzas = new ArrayList<>();

    private ArrayList<Pizza> pizzasServidas = new ArrayList<>();

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public Queue<Pizza> getPedido() {
        return pedidos;
    }

    public void prepararPizza(Pizza pizza) {
        if (pizza != null && pizza.getIngredientesAdicional().size() == 5) {
            pizzas.add(pizza);
        }
    }

    public Boolean servirPedido() {
        Boolean pedidoServido = false;
        Pizza pizzaAchada = null;
        if (!pedidos.isEmpty()) {
            for (Pizza p : pizzas) {
                int ingredientesCorretos = 0;
                for (String pedidoIngrediente : pedidos.peek().getIngredientesAdicional()) {
                    if (p.getIngredientesAdicional().contains(pedidoIngrediente)) {
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
            pizzasServidas.add(pizzaAchada);
            pizzas.remove(pizzaAchada);
        }
        return pedidoServido;
    }


    public String olharPedidoAtual() {
        StringBuilder pedidoAtualStr = new StringBuilder();
        if (!pedidos.isEmpty()) {  //PRA N RETORNAR NULL O PEEK
            Pizza pedidoAtual = pedidos.peek();   //lembrar q o peek pega o primeiro sem remover!!! É APENAS O POLL!!
            pedidoAtualStr.append("Pedido Atual:\n");
            pedidoAtualStr.append("ID: ").append(pedidoAtual.getId()).append("\n");
            pedidoAtualStr.append("Nome: ").append(pedidoAtual.getNomeCliente()).append("\n");
            pedidoAtualStr.append("Ingredientes: ").append(pedidoAtual.getIngredientesAdicional()).append("\n");
            pedidoAtualStr.append("=========================\n");

        }
        return pedidoAtualStr.toString();
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
                int indiceIngrediente = pedidoRandomico.nextInt(pizza.getIngredientes().size());
                String ingrediente = pizza.getIngredientes().get(indiceIngrediente);
                ingredientesAleatorios.add(ingrediente);
            }
            pedido = new Pizza(nome, ingredientesAleatorios);
            pedidos.add(pedido);
        }
    }

    public String calcularEstatisticas(ArrayList<Pizza> pizzas, Queue<Pizza> pedidos) {
        StringBuilder estatisticas = new StringBuilder();

        // Contagem de pizzas servidas
        int totalPizzasServidas = pizzasServidas.size();
        estatisticas.append("Pizzas Servidas: ").append(totalPizzasServidas).append("\n");

        // Contagem de ingredientes utilizados
        HashMap<String, Integer> ingredientesUtilizados = new HashMap<>();
        for (Pizza pizza : pizzasServidas) {
            for (String ingrediente : pizza.getIngredientesAdicional()) {
                ingredientesUtilizados.put(ingrediente, ingredientesUtilizados.getOrDefault(ingrediente, 0) + 1);
            }
        }

        // Encontrar o ingrediente mais pedido
        String ingredienteMaisPedido = "";
        int vezesMaisPedido = 0;
        for (Map.Entry<String, Integer> entry : ingredientesUtilizados.entrySet()) {
            if (entry.getValue() > vezesMaisPedido) {
                ingredienteMaisPedido = entry.getKey();
                vezesMaisPedido = entry.getValue();
            }
        }
        estatisticas.append("Ingrediente Mais Pedido: ").append(ingredienteMaisPedido).append("\n");

        // Encontrar ingredientes não utilizados
        ArrayList<String> ingredientesNaoUtilizados = new ArrayList<>();
        ArrayList<String> ingredientesDisponiveis = pizza.getIngredientes();
        for (String ingrediente : ingredientesDisponiveis) {
            if (!ingredientesUtilizados.containsKey(ingrediente)) {
                ingredientesNaoUtilizados.add(ingrediente);
            }
        }
        estatisticas.append("Ingredientes Não Utilizados: ").append(ingredientesNaoUtilizados).append("\n");

        // Quantidade média de ingredientes corretos
        int totalIngredientesCorretos = 0;
        for (Pizza pizza : pizzasServidas) {
            if (pizza.getIngredientesAdicional().size() == 5) {
                totalIngredientesCorretos += 5;
            }
        }
        double quantidadeMediaIngredientesCorretos = (double) totalIngredientesCorretos / pizzasServidas.size();
        estatisticas.append("Quantidade Média de Ingredientes Corretos por Pizza: ").append(quantidadeMediaIngredientesCorretos).append("\n");

        // Quantidade de pedidos na fila
        int pedidosNaFila = pedidos.size();
        estatisticas.append("Pedidos na Fila: ").append(pedidosNaFila).append("\n");

        return estatisticas.toString();
    }

}
