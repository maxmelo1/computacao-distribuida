/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufms.rmiexemplo;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author max
 */
public class RMIServer extends UnicastRemoteObject implements EstoqueInterface {
    private List<Produto> estoque;
    
    protected RMIServer() throws RemoteException{
        super();
        estoque = new ArrayList<>();
    }

    @Override
    public void cadastrarProduto(Produto p) throws RemoteException {
        System.out.println("Produto adicionado");
        estoque.add(p);
    }

    @Override
    public List<Produto> listarProduto() throws RemoteException {
        System.out.println("Estoque obtido");
        return estoque;
    }
    
    public static void main(String[] args) {
        try {
            RMIServer server = new RMIServer();
            
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("EstoqueService", server);
            
            System.out.println("Servidor online");
            
        } catch (RemoteException ex) {
            System.getLogger(RMIServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
