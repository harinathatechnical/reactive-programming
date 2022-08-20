package com.hari.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoService {

    public Flux<String> fruitsFlux(){
        return Flux.fromIterable(List.of("Mango","Apple"));
    }

    public Mono<String> fruitsMono(){
        return Mono.just("Apple");
    }

    public static void main(String[] args) {
        FluxAndMonoService obj = new FluxAndMonoService();
        obj.fruitsFlux().subscribe(System.out::println);
        obj.fruitsMono().subscribe(System.out::println);
    }
}
