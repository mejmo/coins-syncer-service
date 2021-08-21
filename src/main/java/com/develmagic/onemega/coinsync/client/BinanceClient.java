package com.develmagic.onemega.coinsync.client;

import com.develmagic.onemega.coinsync.client.dto.BinanceCoinListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "binance", url = "${integration.binance.url}")
public interface BinanceClient {

    @GetMapping("/")
    BinanceCoinListResponse getAllCoins();

}
