package com.develmagic.onemega.coinsync.service;

import com.develmagic.onemega.coinsync.client.BinanceClient;
import com.develmagic.onemega.coinsync.client.KrakenClient;
import com.develmagic.onemega.coinsync.model.Coin;
import com.develmagic.onemega.coinsync.model.CoinSource;
//import com.develmagic.onemega.coinsync.model.entities.CoinEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final BinanceClient binanceClient;
    private final KrakenClient krakenClient;

    public List<Coin> getKrakenCoins() {
        return krakenClient.getAllPairs()
                .getResult()
                .values()
                .stream()
                .map(assetPair -> Coin.builder()
                        .name(assetPair.getBase())
                        .source(CoinSource.KRAKEN)
                        .build())
                .collect(Collectors.toList());
    }

}

