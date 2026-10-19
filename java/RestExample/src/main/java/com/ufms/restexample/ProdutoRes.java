/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufms.restexample;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author max
 */
@Path("/produtos")
public class ProdutoRes {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarProduto(){
        Produto p = new Produto("teste", 444.44);
        
        return Response.ok(p).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrar(Produto produto){
        produto.setNome("aaaaa");
        
        return Response.status(Response.Status.CREATED).entity(produto).build();
    }
}
