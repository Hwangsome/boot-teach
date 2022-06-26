package com.bh.reactor.client;

import reactor.netty.http.client.HttpClient;

public class HttpClientApplication {
    public static void main(String[] args) {
        HttpClient client = HttpClient.create();
        client.get()
                .uri("https://www.baidu.com/")
                .response()
                .block();
    }
}
