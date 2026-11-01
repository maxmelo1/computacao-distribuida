package service;

import com.book.BookServiceGrpc;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;

import com.book.Book;
import com.book.GetBookRequest;
import com.book.getBookViaAuthor;

/**
 *
 * @author max
 */
public class BookServiceImpl extends BookServiceGrpc.BookServiceImplBase{
    private static final List<Book> BOOK_DATABASE = List.of(
        Book.newBuilder().setIsbn(101).setTitle("Livro do Desassossego").setAuthor("Fernando Pessoa").build(),
        Book.newBuilder().setIsbn(102).setTitle("Mensagem").setAuthor("Fernando Pessoa").build(),
        Book.newBuilder().setIsbn(103).setTitle("1984").setAuthor("George Orwell").build(),
        Book.newBuilder().setIsbn(104).setTitle("Dom Casmurro").setAuthor("Machado de Assis").build()
    );
    
    
    
    @Override
    public void getBook(GetBookRequest request, StreamObserver<Book> responseObserver) {
        long requestedIsbn = request.getIsbn();
        
        Book book = BOOK_DATABASE.stream()
                .filter(b -> b.getIsbn() == requestedIsbn)
                .findFirst()
                .orElse(Book.newBuilder().setIsbn(0).setTitle("Não encontrado").setAuthor("-").build());

        responseObserver.onNext(book);
        responseObserver.onCompleted();
    }
    
    
    
    
    
    
    
    @Override
    public void geBooksViaAuthor(getBookViaAuthor request, StreamObserver<Book> responseObserver) {
        String author = request.getAuthor();
        
        for (Book book : BOOK_DATABASE) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                responseObserver.onNext(book);
            }
        }
        responseObserver.onCompleted();
    }
    
    @Override
    public StreamObserver<GetBookRequest> getGreatestBook(StreamObserver<Book> responseObserver) {
        
        return new StreamObserver<GetBookRequest>() {
            private Book greatestBook = null;

            @Override
            public void onNext(GetBookRequest request) {
                BOOK_DATABASE.stream()
                        .filter(b -> b.getIsbn() == request.getIsbn())
                        .findFirst()
                        .ifPresent(book -> {
                            if (greatestBook == null || book.getIsbn() > greatestBook.getIsbn()) {
                                greatestBook = book;
                            }
                        });
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Erro no Client Streaming: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                if (greatestBook != null) {
                    responseObserver.onNext(greatestBook);
                } else {
                    responseObserver.onNext(Book.newBuilder().setTitle("Nenhum livro encontrado").build());
                }
                responseObserver.onCompleted();
            }
        };
    }
    
    @Override
    public StreamObserver<GetBookRequest> getBooks(StreamObserver<Book> responseObserver) {
        return new StreamObserver<GetBookRequest>() {
            @Override
            public void onNext(GetBookRequest request) {
                
                Book found = BOOK_DATABASE.stream()
                        .filter(b -> b.getIsbn() == request.getIsbn())
                        .findFirst()
                        .orElse(Book.newBuilder().setIsbn(request.getIsbn()).setTitle("Desconhecido").setAuthor("-").build());

                responseObserver.onNext(found);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Erro no Bidirectional Streaming: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
