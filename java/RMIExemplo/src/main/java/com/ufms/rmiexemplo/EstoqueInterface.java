/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufms.rmiexemplo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author max
 */
public interface EstoqueInterface extends Remote {
    void cadastrarProduto(Produto p) throws RemoteException;
    
    List<Produto> listarProduto() throws RemoteException;
}
