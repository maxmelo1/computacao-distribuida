/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import com.book.Book;
import com.book.BookServiceGrpc;
import com.book.GetBookRequest;
import com.book.getBookViaAuthor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author max
 */
public class BookClient {
    public static void main(String[] args) throws InterruptedException {
        
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        
        
        
        
        BookServiceGrpc.BookServiceBlockingStub blockingStub = BookServiceGrpc.newBlockingStub(channel);
        BookServiceGrpc.BookServiceStub asyncStub = BookServiceGrpc.newStub(channel);
        
        
        Book book = blockingStub.getBook(GetBookRequest.getDefaultInstance().newBuilder().setIsbn(104).build());
        System.out.println("Livro retornado: " + book.getTitle() + " - " + book.getAuthor());

        
        Iterator<Book> booksAuthor = blockingStub.geBooksViaAuthor(getBookViaAuthor.newBuilder().setAuthor("Fernando Pessoa").build());
        while (booksAuthor.hasNext()) {
            Book b = booksAuthor.next();
            System.out.println("Livro encontrado: " + b.getTitle());
        }

        
        CountDownLatch latchClientStream = new CountDownLatch(1);
        StreamObserver<GetBookRequest> clientStream = asyncStub.getGreatestBook(new StreamObserver<Book>() {
            @Override
            public void onNext(Book b) {
                System.out.println("Maior Livro processado pelo servidor: " + b.getTitle() + " (ISBN: " + b.getIsbn() + ")");
            }

            @Override
            public void onError(Throwable t) {}

            @Override
            public void onCompleted() {
                latchClientStream.countDown();
            }
        });

        clientStream.onNext(GetBookRequest.newBuilder().setIsbn(101).build());
        clientStream.onNext(GetBookRequest.newBuilder().setIsbn(104).build());
        clientStream.onNext(GetBookRequest.newBuilder().setIsbn(102).build());
        clientStream.onCompleted();
        latchClientStream.await(3, TimeUnit.SECONDS);

        
        
        CountDownLatch latchBidiStream = new CountDownLatch(1);
        StreamObserver<GetBookRequest> bidiStream = asyncStub.getBooks(new StreamObserver<Book>() {
            @Override
            public void onNext(Book b) {
                System.out.println("Resposta em tempo real: ISBN " + b.getIsbn() + " -> " + b.getTitle());
            }

            @Override
            public void onError(Throwable t) {}

            @Override
            public void onCompleted() {
                latchBidiStream.countDown();
            }
        });

        bidiStream.onNext(GetBookRequest.newBuilder().setIsbn(103).build());
        bidiStream.onNext(GetBookRequest.newBuilder().setIsbn(104).build());
        bidiStream.onNext(GetBookRequest.newBuilder().setIsbn(999).build());
        bidiStream.onCompleted();
        latchBidiStream.await(3, TimeUnit.SECONDS);

        channel.shutdown();
    }
}
