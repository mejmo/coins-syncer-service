package com.develmagic.onemega.coinsync.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.Data;
import lombok.Getter;

@Data
public class KrakenCoinListResponse {
    private Map<String, AssetPair> result;

    @Data
    public static class AssetPair {
        private String base;
        private String quote;

        @JsonProperty("altname")
        private String altName;

        @JsonProperty("wsname")
        private String wsName;
    }
}
