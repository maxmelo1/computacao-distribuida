/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ufms.clientrest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author max
 */
public class ClientRest {
    
    private static final String URL_API = "http://localhost:8080/RestExample/api/produtos";

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

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API).create(URL_API).create(URL_API))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Corpo: " + response.body());
    }

    private static void cadastrarProduto() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String jsonNovoProduto = """
                {
                    "nome": "Mouse Gamer sem fio",
                    "preco": 350.50
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonNovoProduto))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Corpo: " + response.body());
    }
}
