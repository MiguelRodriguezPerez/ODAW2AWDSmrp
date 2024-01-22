package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.CambioData;

import reactor.core.publisher.Mono;

@Service
public class CambioService {

    @Autowired
    WebClient webClient;

    public Double calcularImporteCambiado(String monedaOrigen, String monedaDestino, Double importe){
        CambioData cambioData = obtenerTasaCambio(monedaOrigen,monedaDestino).block();
        float tasaCambio = cambioData.getRates().get(monedaDestino);
        return importe * tasaCambio;       
    }

    public Mono<CambioData> obtenerTasaCambio(String monedaOrigen, String monedaDestino) throws RuntimeException{
        if(monedaOrigen.equals(monedaDestino)) throw new RuntimeException("Ha elegido la misma divisa");
        String url = "/latest?from="+monedaOrigen+"&to="+monedaDestino;
        return webClient.get()
        .uri(url)
        .retrieve()
        .bodyToMono(CambioData.class);
    }
}
