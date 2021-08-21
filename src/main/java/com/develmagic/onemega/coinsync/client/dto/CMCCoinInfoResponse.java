package com.develmagic.onemega.coinsync.client.dto;

import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CMCCoinInfoResponse {

    private Map<String, CMCData> data;

    @Data
    public static class CMCData {
        private CMCCoinUrl urls;
        private String symbol;
        private String name;
        private String slug;


        public static class CMCCoinUrl {
            @Setter
            private List<String> reddit;

            @Setter
            private List<String> twitter;

            public String getRedditUrl() {
                return reddit.size() == 1 ? reddit.get(0) : null;
            }

            public String getTwitterUrl() {
                return twitter.size() == 1 ? twitter.get(0) : null;
            }
        }
    }

}
