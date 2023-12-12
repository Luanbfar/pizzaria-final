package br.com.pizzaria.entidades.acoes;

public class NaoExistemPedidos extends Exception {
	public NaoExistemPedidos() {
		super("\nNão existem pedidos cadastrados no momento!!");
	}

}
