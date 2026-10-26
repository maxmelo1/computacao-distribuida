/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufms.clientrest;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author max
 */
public class ClientRest2 {
    private static final String URL_API = "http://localhost:8080/RestExample/api/produtos";
    private static final Gson gson = new Gson();
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        
        
        try{
            consultarProduto();
            
            cadastrarProduto();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private static void consultarProduto() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL_API)).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Produto produto = gson.fromJson(response.body(), Produto.class);

        System.out.println( produto);
    }

    private static void cadastrarProduto() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        Produto produto = new Produto("Monitor Ultrawide LG", 1250.00);

        String produtoJson = gson.toJson(produto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(produtoJson))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201) {
            Produto produtoResponse = gson.fromJson(response.body(), Produto.class);
            System.out.println(produtoResponse.getNome()+ " cadastrado");
        }
    }
}
