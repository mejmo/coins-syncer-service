package com.develmagic.onemega.coinsync.service;

import com.develmagic.onemega.coinsync.client.CoinmarketCapClient;
import com.develmagic.onemega.coinsync.client.exception.CMCCoinNotFound;
import com.develmagic.onemega.coinsync.model.Coin;
import com.develmagic.onemega.coinsync.model.CoinSource;
import feign.FeignException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoinmarketCapService {

    private final CoinmarketCapClient coinmarketCapClient;

    public Optional<Coin> getBySymbol(String symbol) {
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
            return Optional.empty();
        }
    }

}
