/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ufms.rmiexemplo;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

/**
 *
 * @author max
 */
public class RMIClient {

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            
            EstoqueInterface estoque = (EstoqueInterface) reg.lookup("EstoqueService");
            
            Produto p1 = new Produto("placa de video", 9000.00);
            Produto p2 = new Produto("processador", 4000.00);
            
            estoque.cadastrarProduto(p1);
            estoque.cadastrarProduto(p2);
            
            List<Produto> lprod = estoque.listarProduto();
            
            for(Produto p : lprod){
                System.out.println(p);
            }
            
        } catch (RemoteException ex) {
            System.getLogger(RMIClient.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (NotBoundException ex) {
            System.getLogger(RMIClient.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
