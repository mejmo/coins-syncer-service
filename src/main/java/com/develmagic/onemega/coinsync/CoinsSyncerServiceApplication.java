package com.develmagic.onemega.coinsync;

import com.develmagic.onemega.coinsync.client.CoinmarketCapClient;
import javax.annotation.PostConstruct;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CoinsSyncerServiceApplication {

    @Autowired
    CoinmarketCapClient capClient;

    @PostConstruct
    public void kokot() {
        capClient.getCoinInfoBySlug("bitcoin");
    }

    public static void main(String[] args) {
        SpringApplication.run(CoinsSyncerServiceApplication.class, args);
    }

}
