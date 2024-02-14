package br.com.pizzaria.entidades.exceptions;

public class NaoExistemPedidos extends Exception {
	public NaoExistemPedidos() {
		super("\nNão existem pedidos cadastrados no momento!!");
	}

}
