package com.develmagic.onemega.coinsync.client.dto;

import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.Getter;

@Data
public class CMCCoinInfoResponse {

    @Getter
    private Map<String, CMCData> data;

    @Data
    public static class CMCData {
        private CMCCoinUrl urls;
    }

    @Data
    public static class CMCCoinUrl {
        private List<String> reddit;
        private List<String> twitter;
    }

}
