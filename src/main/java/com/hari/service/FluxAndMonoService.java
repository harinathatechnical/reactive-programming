package com.hari.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoService {

    public Flux<String> fruitsFlux(){
        return Flux.fromIterable(List.of("Mango","Apple"))
                .log();
    }

    public Flux<String> fruitsFluxMap(){
        return Flux.fromIterable(List.of("Mango","Apple"))
                .map(String::toUpperCase)
                .log();
    }


    public Flux<String> fruitsFluxFilter(int i){
        return Flux.fromIterable(List.of("Mango","Banana"))
                .filter(s -> s.length()>i);
    }

    public Flux<String> fruitsFluxFilterMap(int i){
        return Flux.fromIterable(List.of("Mango","Banana"))
                .filter(s -> s.length()>i)
                .map(String::toUpperCase);
    }

    public Flux<String> fruitsFluxFlatMap(){
        return Flux.fromIterable(List.of("Mango","Apple"))
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsFluxFlatMapAsync(){
        return Flux.fromIterable(List.of("Mango","Apple"))
                .flatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                ))
                .log();
    }

    public Flux<String> fruitsFluxConcatMap(){
        return Flux.fromIterable(List.of("Mango","Apple"))
                .concatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                ))
                .log();
    }

    public Mono<List<String>> fruitsMonoFlatMap(){
        return Mono.just("Apple")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    public Flux<String> fruitsMonoFlatMapMany(){
        return Mono.just("Apple")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsFluxTransform(int num){

        Function<Flux<String>,Flux<String>> filterData =
                data -> data.filter(s -> s.length()>num);

        return Flux.fromIterable(List.of("Mango","Banana"))
                .transform(filterData)
                .log();
                //.filter(s -> s.length()>num);
    }

    public Flux<String> fruitsFluxTransformDefaultIfEmpty(int num){

        Function<Flux<String>,Flux<String>> filterData =
                data -> data.filter(s -> s.length()>num);

        return Flux.fromIterable(List.of("Mango","Banana"))
                .transform(filterData)
                .defaultIfEmpty("Default")
                .log();
        //.filter(s -> s.length()>num);
    }

    public Flux<String> fruitsFluxTransformSwitchIfEmpty(int num){

        Function<Flux<String>,Flux<String>> filterData =
                data -> data.filter(s -> s.length()>num);

        return Flux.fromIterable(List.of("Mango","Banana"))
                .transform(filterData)
                .switchIfEmpty(Flux.just("PineApple"))
                .log();
        //.filter(s -> s.length()>num);
    }


    public Flux<String> fruitsFluxConcat(){
        var fruits = Flux.just("Mango","Banana");
        var veggies = Flux.just("Tomato","Lemon");
        return Flux.concat(fruits,veggies);
    }

    public Flux<String> fruitsFluxConcatWith(){
        var fruits = Flux.just("Mango","Banana");
        var veggies = Flux.just("Tomato","Lemon");
        return fruits.concatWith(veggies);
    }

    public Flux<String> fruitsMonoConcatWith(){
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Tomato");
        return fruits.concatWith(veggies);
    }

    public Flux<String> fruitsFluxMerge(){
        var fruits = Flux.just("Mango","Banana")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato","Lemon")
                .delayElements(Duration.ofMillis(75));
        return Flux.merge(fruits,veggies);
    }

    public Flux<String> fruitsFluxMergeWith(){
        var fruits = Flux.just("Mango","Banana")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato","Lemon")
                .delayElements(Duration.ofMillis(75));
        return fruits.mergeWith(veggies);
    }

    public Flux<String> fruitsFluxMergeWithSequential(){
        var fruits = Flux.just("Mango","Banana")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato","Lemon")
                .delayElements(Duration.ofMillis(75));
        return Flux.mergeSequential(fruits,veggies);
    }

    public Flux<String> fruitsFluxZip(){
        var fruits = Flux.just("Mango","Banana");
        var veggies = Flux.just("Tomato","Lemon");
       return Flux.zip(fruits,veggies,(first,second)->first+second).log();
    }

    public Flux<String> fruitsFluxZipWith(){
        var fruits = Flux.just("Mango","Banana");
        var veggies = Flux.just("Tomato","Lemon");
        return fruits.zipWith(veggies,(first,second)->first+second).log();
    }

    public Flux<String> fruitsFluxZipWithTuple(){
        var fruits = Flux.just("Mango","Banana");
        var veggies = Flux.just("Tomato","Lemon");
        var leaves = Flux.just("spinach","coriander");
        return Flux.zip(fruits,veggies,leaves)
                .map(objects -> objects.getT1()+objects.getT2()+objects.getT3());
    }

    public Mono<String> fruitsMonoZipWith(){
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Tomato");
        return fruits.zipWith(veggies,(first,second)->first+second).log();
    }

    public Mono<String> fruitsMono(){
        return Mono.just("Apple").log();
    }

    public static void main(String[] args) {
        FluxAndMonoService obj = new FluxAndMonoService();
        obj.fruitsFlux().subscribe(System.out::println);
        obj.fruitsMono().subscribe(System.out::println);
    }
}
