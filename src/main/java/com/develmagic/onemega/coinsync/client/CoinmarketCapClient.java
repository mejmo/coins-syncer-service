package com.develmagic.onemega.coinsync.client;

import com.develmagic.onemega.coinsync.client.dto.CMCCoinInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "coinmarketcap", url = "${integration.coinmarketcap.url}")
public interface CoinmarketCapClient {

    @GetMapping("/v1/cryptocurrency/info")
    CMCCoinInfoResponse getCoinInfoBySlug(@RequestParam("slug") String slug);

    @GetMapping("/v1/cryptocurrency/info")
    CMCCoinInfoResponse getCoinInfoByIdg(@RequestParam("id") int id);

}
