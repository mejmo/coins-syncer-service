package com.develmagic.onemega.coinsync.client;

import com.develmagic.onemega.coinsync.client.dto.KrakenCoinListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kraken", url = "${integration.binance.url}")
public interface KrakenClient {

    @GetMapping("/public/AssetPairs")
    KrakenCoinListResponse getAllPairs();

    @GetMapping("/public/AssetPairs")
    KrakenCoinListResponse getPair(@RequestParam("pair") String pair);


//    @Slf4j
//    static class KrakenClientFeignConfiguration {
//
//        @Bean
//        public RequestInterceptor krakenRequestInterceptor(
//                @Value("${integration.kraken.key}") String key,
//                @Value("${integration.kraken.secret}") String secret) {
//            return requestTemplate -> {
//                requestTemplate.request();
//                requestTemplate.header("API-Key", key);
//                requestTemplate.header("API-Sign", getKrakenSignature(secret));
//                requestTemplate.header("Accept", ContentType.APPLICATION_JSON.getMimeType());
//            };
//        }
//
//        private static String getKrakenSignature(String urlPath, Object data, String secret) {
//            try {
//                final MessageDigest md = MessageDigest.getInstance("SHA-256");
//                final String nonce = String.valueOf(System.currentTimeMillis());
//                md.update((nonce + data).getBytes());
//                Mac mac = Mac.getInstance("HmacSHA512");
//                mac.init(new SecretKeySpec(Base64.getDecoder().decode(secret.getBytes()), "HmacSHA512"));
//                mac.update(urlPath.getBytes());
//                final String signature = new String(Base64.getEncoder().encode(mac.doFinal(md.digest())));
//            } catch (NoSuchAlgorithmException | InvalidKeyException e) {
//                log.error("Cannot create signature for Kraken", e);
//                throw new CoinSyncerCryptoException();
//            }
//        }
//    }

}
