/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufms.restexample;

import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author max
 */
@ApplicationScoped
public class Produto {
    private String nome;
    private double preco;
    
    public Produto(){
        
    }
    
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() { 
        return nome; 
    }
    public synchronized void setNome(String nome){ 
        this.nome = nome; 
    }
    public double getPreco() { 
        return preco; 
    }
    public synchronized void setPreco(double preco){ 
        this.preco = preco; 
    }
    
}
