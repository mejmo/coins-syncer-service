package com.develmagic.onemega.coinsync.service;

import com.develmagic.onemega.coinsync.client.BinanceClient;
import com.develmagic.onemega.coinsync.client.KrakenClient;
import com.develmagic.onemega.coinsync.model.Coin;
import com.develmagic.onemega.coinsync.model.CoinSource;
//import com.develmagic.onemega.coinsync.model.entities.CoinEntity;
import com.develmagic.onemega.coinsync.model.FiatCurrency;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final BinanceClient binanceClient;
    private final KrakenClient krakenClient;

    /**
     * Gets only EUR pairs because we trade just against EUR
     *
     * @return
     */
    public List<Coin> getKrakenCoins() {
        log.debug("Getting all coins from Kraken");
        return krakenClient.getAllPairs()
                .getResult()
                .values()
                .stream()
                .filter(p -> p.getQuote().contains(FiatCurrency.EUR.toString()))
                .map(assetPair -> Coin.builder()
                        .symbol(assetPair.getBase().replaceAll("^X(.*)$", "$1"))
                        .source(CoinSource.KRAKEN)
                        .build())
                .collect(Collectors.toList());
    }

}

