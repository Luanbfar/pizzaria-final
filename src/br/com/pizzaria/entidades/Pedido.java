package br.com.pizzaria.entidades;

import java.util.ArrayList;

public class Pedido {
    private int id;
    private String nomeCliente;

    private static int contador = 0;

    private ArrayList<String> ingredientesAleatorios;

    public Pedido(String nomeCliente, ArrayList<String> ingredientesAleatorios) {
        this.nomeCliente = nomeCliente;
        this.ingredientesAleatorios = ingredientesAleatorios;
        this.id = contador++;

    }

    public int getId() {
        return id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public ArrayList<String> getIngredientesAleatorios() {
        return ingredientesAleatorios;
    }
}
