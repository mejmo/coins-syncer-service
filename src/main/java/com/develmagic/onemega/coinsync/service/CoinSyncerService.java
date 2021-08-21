package com.develmagic.onemega.coinsync.service;

import com.develmagic.onemega.coinsync.client.CoinmarketCapClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoinSyncerService implements InitializingBean {

    private final ExchangeService exchangeService;
    private final CoinmarketCapService coinmarketCapService;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Starting initial synchronization");
        coinmarketCapService.getBySymbol("BTC");

//        exchangeService.getKrakenCoins()
//                .stream()
//                .map(f -> coinmarketCapClient.getCoinInfoBySymbol(f.getName()))
    }

}
