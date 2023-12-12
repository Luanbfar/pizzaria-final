package br.com.pizzaria.entidades.acoes;

public class NaoExistemPedidos extends Exception {
	public NaoExistemPedidos() {
		super("Não existem pedidos cadastrados no momento!!");
	}

}
