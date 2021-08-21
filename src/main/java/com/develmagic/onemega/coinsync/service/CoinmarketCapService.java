package com.develmagic.onemega.coinsync.service;

import com.develmagic.onemega.coinsync.client.CoinmarketCapClient;
import com.develmagic.onemega.coinsync.client.exception.CMCCoinNotFound;
import com.develmagic.onemega.coinsync.client.exception.CMCRateLimit;
import com.develmagic.onemega.coinsync.model.Coin;
import com.develmagic.onemega.coinsync.model.CoinSource;
import feign.FeignException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoinmarketCapService {

    private final CoinmarketCapClient coinmarketCapClient;

    @Cacheable("cmcCache")
    public Optional<Coin> getBySymbol(String symbol) {
        log.debug("Getting coin {} from CMC", symbol);
        try {
            return coinmarketCapClient.getCoinInfoBySymbol(symbol)
                    .getData()
                    .entrySet()
                    .stream()
                    .map(e -> Coin.builder()
                            .symbol(e.getKey())
                                    .name(e.getValue().getName())
                                    .symbol(e.getValue().getSymbol())
                                    .slug(e.getValue().getSlug())
                            .source(CoinSource.COINMARKETCAP)
                            .redditUrl(e.getValue().getUrls().getRedditUrl())
                            .twitterUrl(e.getValue().getUrls().getTwitterUrl())
                            .build()
                    ).findFirst();
        } catch (CMCCoinNotFound e) {
            log.info("Coin {} not found", symbol);
            return Optional.empty();
        } catch (CMCRateLimit e) {
            log.info("Rate limit exceeded for symbol {}. Waiting 1000ms", symbol);
            try {
                TimeUnit.SECONDS.sleep(1);
                return this.getBySymbol(symbol);
            } catch (InterruptedException ex) {
                return Optional.empty();
            }
        }
    }

}
