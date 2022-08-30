package com.hari.service;

import com.hari.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {

    public Flux<BookInfo> getBooks(){
        var books = List.of(
                new BookInfo(1,"Book One","Author One","1234456"),
                new BookInfo(2,"Book Two","Author Two","2234456"),
                new BookInfo(2,"Book Three","Author Three","3234456")
        );
        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookById(long bookById){
        var book = new BookInfo(bookById,"Book One","Author One","1234456");
        return Mono.just(book);
    }

}
