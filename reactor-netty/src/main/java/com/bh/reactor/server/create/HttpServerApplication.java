package com.bh.reactor.server.create;

import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

public class HttpServerApplication {
    public static void main(String[] args) {
        DisposableServer server =
                HttpServer.create()
                        .host("localhost")
                        .port(8080)
                        .bindNow();

        server.onDispose()
                .block();

    }
}
