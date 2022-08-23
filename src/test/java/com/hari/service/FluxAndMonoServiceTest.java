package com.hari.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;


class FluxAndMonoServiceTest {

    FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();

    @Test
    public void testFlux(){
        var fruitFlux = fluxAndMonoService.fruitsFlux();

        StepVerifier.create(fruitFlux)
                .expectNext("Mango","Apple")
                .verifyComplete();
    }

    @Test
    public void testMono(){
        var fruitMono = fluxAndMonoService.fruitsMono();
        StepVerifier.create(fruitMono)
                .expectNext("Apple")
                .verifyComplete();
    }

    @Test
    public void fruitFluxMap(){
        var fruitFlux = fluxAndMonoService.fruitsFluxMap();

        StepVerifier.create(fruitFlux)
                .expectNext("MANGO","APPLE")
                .verifyComplete();
    }

    @Test
    public void fruitFluxFilter(){
        var fruitFlux = fluxAndMonoService.fruitsFluxFilter(5);

        StepVerifier.create(fruitFlux)
                .expectNext("Banana")
                .verifyComplete();
    }

    @Test
    public void fruitFluxFilterMap(){
        var fruitFlux = fluxAndMonoService.fruitsFluxFilterMap(5);

        StepVerifier.create(fruitFlux)
                .expectNext("BANANA")
                .verifyComplete();
    }

    @Test
    public void fruitFluxFlatMap(){
        var fruitFlux = fluxAndMonoService.fruitsFluxFlatMap();

        StepVerifier.create(fruitFlux)
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    public void fruitFluxFlatMapAync(){
        var fruitFlux = fluxAndMonoService.fruitsFluxFlatMapAsync();

        StepVerifier.create(fruitFlux)
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    void fruitsMonoFlatMap() {
        var fruitFlux = fluxAndMonoService.fruitsMonoFlatMap();

        StepVerifier.create(fruitFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatMap() {
        var fruitFlux = fluxAndMonoService.fruitsFluxConcatMap();

        StepVerifier.create(fruitFlux)
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    void fruitsMonoFlatMapMany() {
        var fruitFlux = fluxAndMonoService.fruitsMonoFlatMapMany();

        StepVerifier.create(fruitFlux)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransform() {
        var fruitFlux = fluxAndMonoService.fruitsFluxTransform(5);

        StepVerifier.create(fruitFlux)
                .expectNext("Banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformDefaultIfEmpty() {
        var fruitFlux = fluxAndMonoService.fruitsFluxTransformDefaultIfEmpty(50);

        StepVerifier.create(fruitFlux)
                .expectNext("Default")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformSwitchIfEmpty() {
        var fruitFlux = fluxAndMonoService.fruitsFluxTransformSwitchIfEmpty(50);

        StepVerifier.create(fruitFlux)
                .expectNext("PineApple")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcat() {
        var fruitFlux = fluxAndMonoService.fruitsFluxConcat().log();

        StepVerifier.create(fruitFlux)
                .expectNext("Mango","Banana","Tomato","Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatWith() {
        var fruitFlux = fluxAndMonoService.fruitsFluxConcatWith().log();

        StepVerifier.create(fruitFlux)
                .expectNext("Mango","Banana","Tomato","Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsMonoConcatWith() {

        var fruitFlux = fluxAndMonoService.fruitsMonoConcatWith().log();

        StepVerifier.create(fruitFlux)
                .expectNext("Mango","Tomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMerge() {
        var fruitFlux = fluxAndMonoService.fruitsFluxMerge().log();

        StepVerifier.create(fruitFlux)
                .expectNext("Mango","Tomato","Banana","Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWith() {
        var fruitFlux = fluxAndMonoService.fruitsFluxMergeWith().log();

        StepVerifier.create(fruitFlux)
                .expectNext("Mango","Tomato","Banana","Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWithSequential() {
        var fruitFlux = fluxAndMonoService.fruitsFluxMergeWithSequential().log();
        StepVerifier.create(fruitFlux)
                .expectNext("Mango","Banana","Tomato","Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZip() {
        var fruitFlux = fluxAndMonoService.fruitsFluxZip().log();
        StepVerifier.create(fruitFlux)
                .expectNext("MangoTomato","BananaLemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipWith() {
        var fruitFlux = fluxAndMonoService.fruitsFluxZipWith().log();
        StepVerifier.create(fruitFlux)
                .expectNext("MangoTomato","BananaLemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipWithTuple() {
        var fruitFlux = fluxAndMonoService.fruitsFluxZipWithTuple().log();
        StepVerifier.create(fruitFlux)
                .expectNext("MangoTomatospinach","BananaLemoncoriander")
                .verifyComplete();
    }

    @Test
    void fruitsMonoZipWith() {
        var fruitFlux = fluxAndMonoService.fruitsMonoZipWith().log();
        StepVerifier.create(fruitFlux)
                .expectNext("MangoTomato")
                .verifyComplete();
    }
}