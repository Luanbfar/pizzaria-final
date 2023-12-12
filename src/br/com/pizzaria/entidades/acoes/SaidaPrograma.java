package br.com.pizzaria.entidades.acoes;

import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.pizzaria.entidades.Pizza;

public class SaidaPrograma {
    public static void main(String[] args)
            throws HeadlessException, NaoExistemPedidos, IngredienteInsuficiente, NumberFormatException {
        Metodo m = new Metodo();
        Pizza p = new Pizza();

        int opcao = 0;
        String menu = """
                1) Receber um pedido aleatório
                2) Olhar pedido atual
                3) Preparar uma pizza
                4) Servir pedido
                5) Estatísticas dos pedidos
                6) Olhar todos os Pedidos            					 //VER SE REALMENTE FUNCIONA
                7) Listar pizzas Criadas								//VER SE REALMENTE FUNCIONA
                8) Sair do programa

                """;

        do {
            try {
                String opcaoStr = JOptionPane.showInputDialog(null, menu + "digite a opção desejada:");
                opcao = Integer.parseInt(opcaoStr);

                switch (opcao) {
                    case 1:
                        // RECEBER O PEDIDO DO CLIENTE Q É ALEATORIO

                        String cliente = JOptionPane.showInputDialog(null, "Nome do cliente: ");
                        if (cliente == null || cliente.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Pedido Não foi cadastrado!!");
                        } else {
                            m.receberUmPedidoAleatorio(cliente);
                            JOptionPane.showMessageDialog(null, "Pedido recebido com sucesso!!");
                        }
                        break;
                    case 2:
                        // OLHAR O PEDIDO DO CLIENTE.
                        try {
                            if (m.getPedido().isEmpty()) {
                                throw new NaoExistemPedidos();
                            } else {
                                JOptionPane.showMessageDialog(null, "Pedido atual: " + m.olharPedidoAtual());
                            }

                        } catch (NaoExistemPedidos erro) {
                            JOptionPane.showMessageDialog(null, erro);
                        }
                        break;
                    case 3:
                        // PREPARAR PIZZA
                        try {
                            ArrayList<String> ingredientesPraPorNaPizza = new ArrayList<>();
                            String ingrediente = null;
                            for (int i = 0; i < 5; i++) {
                                ingrediente = JOptionPane.showInputDialog(null,
                                        p.ingredientesStr() + "Digite o ingrediente da pizza: ");
                                ingredientesPraPorNaPizza.add(ingrediente);
                            }
                            if (ingrediente != null && !ingrediente.isEmpty() && ingredientesPraPorNaPizza.size() == 5
                                    && p.getIngredientes().contains(ingrediente)) {
                                m.prepararPizza(new Pizza(ingredientesPraPorNaPizza)); // Construtor com sobrecarga.
                                JOptionPane.showMessageDialog(null, "Pizza preparada com sucesso!!");

                            } else if (!p.getIngredientes().contains(ingrediente)) {
                                JOptionPane.showMessageDialog(null,
                                        "Um dos ingredientes Inseridos não existe no cardápio!");

                            } else {
                                throw new IngredienteInsuficiente();
                            }
                        } catch (IngredienteInsuficiente e) {
                            JOptionPane.showMessageDialog(null, e); // Assim que usa o exception

                        }
                        break;

                    case 4:
                        //SERVIR OS PEDIDOS AQUI!! FALTA RESOLVER A QUESTAO DE COMPARAR
                        try {
                            if (m.getPedido().isEmpty()) {
                                throw new NaoExistemPedidos();
                            } else if (m.servirPedido()) {
                                JOptionPane.showMessageDialog(null, "Pedido servido com sucesso!!");
                            } else {
                                JOptionPane.showMessageDialog(null, "O pedido não foi servido.");
                            }
                        } catch (NaoExistemPedidos e) {
                            JOptionPane.showMessageDialog(null, e); // Assim que usa o exception

                        }
                        break;
                    case 5:
                        // Lógica para Estatísticas dos pedidos
                        break;
                    case 6:
                        //CASE Q NAO PRECISA TER, É SO PRA VER SE FUNCIONA VER TODOS OS PEEDIDOS
                        try {
                            if (m.getPedido().isEmpty()) {
                                throw new NaoExistemPedidos();

                            } else {
                                JOptionPane.showMessageDialog(null, "Pedido atual: " + m.todosOsPedidos());

                            }

                        } catch (NaoExistemPedidos erro) {
                            JOptionPane.showMessageDialog(null, erro);

                        }
                        // Lógica para Olhar todos os Pedidos
                        break;
                    case 7:
                        //CASE Q NAO PRECISA TER, É SO PRA VER SE FUNCIONA VER TODAS AS PIZZAS CRIADAS!

                        JOptionPane.showMessageDialog(null, m.listarPizzaCriada());
                        break;
                    case 8:
                        // Lógica para Sair do programa
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida!");
                        break;
                }
            } catch (NumberFormatException erro) {
                JOptionPane.showMessageDialog(null, "Insira valores válidos!\n" + erro);
            }
        } while (opcao != 8);

        JOptionPane.showMessageDialog(null, "Obrigado por confiar na Pizzaria!");


    }
}