/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufms.rmiexemplo;

import java.io.Serializable;

/**
 *
 * @author max
 */
public class Produto implements Serializable{
    private String nome;
    private double preco;
    
    private static final long serialVersionUID = 1L;
    
    public Produto() {
    }

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    @Override
    public String toString() {
        return "Produto: " + nome + ", Preço: R$ " + preco;
    }
    
}
