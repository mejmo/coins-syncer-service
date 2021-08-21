package com.develmagic.onemega.coinsync.client;

import com.develmagic.onemega.coinsync.client.CoinmarketCapClient.CMCFeignConfiguration;
import com.develmagic.onemega.coinsync.client.dto.CMCCoinInfoResponse;
import com.develmagic.onemega.coinsync.client.exception.CMCCoinNotFound;
import com.develmagic.onemega.coinsync.client.exception.CMCRateLimit;
import feign.codec.ErrorDecoder;
import feign.error.AnnotationErrorDecoder;
import feign.error.ErrorCodes;
import feign.error.ErrorHandling;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "coinmarketcap", url = "${integration.coinmarketcap.url}", configuration = CMCFeignConfiguration.class)
@ErrorHandling(codeSpecific =
        {
                @ErrorCodes(codes = {400}, generate = CMCCoinNotFound.class),
                @ErrorCodes(codes = {429}, generate = CMCRateLimit.class)
        }
)

public interface CoinmarketCapClient {

    @Cacheable(value = "cmcCache", key = "{symbol, #symbol}")
    @GetMapping("/v1/cryptocurrency/info")
    @ErrorHandling(codeSpecific =
            {
                    @ErrorCodes(codes = {400}, generate = CMCCoinNotFound.class),
            }
    )
    CMCCoinInfoResponse getCoinInfoBySymbol(@RequestParam("symbol") String symbol) throws CMCCoinNotFound, CMCRateLimit;

    @Cacheable(value = "cmcCache", key = "{slug, #slug}")
    @GetMapping("/v1/cryptocurrency/info")
    @ErrorHandling(codeSpecific =
            {
                    @ErrorCodes(codes = {400}, generate = CMCCoinNotFound.class)
            }
    )
    CMCCoinInfoResponse getCoinInfoBySlug(@RequestParam("slug") String slug) throws CMCCoinNotFound, CMCRateLimit;

    @Cacheable(value = "cmcCache", key = "{id, #id}")
    @GetMapping("/v1/cryptocurrency/info")
    @ErrorHandling(codeSpecific =
            {
                    @ErrorCodes(codes = {400}, generate = CMCCoinNotFound.class)
            }
    )
    CMCCoinInfoResponse getCoinInfoById(@RequestParam("id") int id)  throws CMCCoinNotFound, CMCRateLimit;

    class CMCFeignConfiguration {
        @Bean
        ErrorDecoder errorDecoder() {
            return AnnotationErrorDecoder.builderFor(CoinmarketCapClient.class).build();
        }
    }

}
