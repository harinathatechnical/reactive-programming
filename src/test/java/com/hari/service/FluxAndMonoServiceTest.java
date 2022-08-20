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

}