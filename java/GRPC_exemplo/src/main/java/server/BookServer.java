/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import service.BookServiceImpl;

/**
 *
 * @author max
 */
public class BookServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50051;
        Server server = ServerBuilder.forPort(port)
                .addService(new BookServiceImpl())
                .build();

        server.start();
        System.out.println("Servidor gRPC iniciado na porta " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Encerrando servidor...");
            server.shutdown();
        }));

        server.awaitTermination();
    }
}
