package com.develmagic.onemega.coinsync.model;

import lombok.Builder;
import lombok.Getter;

@Builder
public class Coin {

    @Getter
    private String name;

    @Getter
    private String symbol;

    @Getter
    private String slug;

    @Getter
    private CoinSource source;

    @Getter
    private String redditUrl;

    @Getter
    private String twitterUrl;

}
