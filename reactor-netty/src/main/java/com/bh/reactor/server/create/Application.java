package com.bh.reactor.server.create;

import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

public class Application {

	public static void main(String[] args) {
		//返回的DisposableServer 提供了一个简单的服务器 API，包括disposeNow()，它以阻塞方式关闭服务器。
		DisposableServer server =
				//创建一个TcpServer 准备好进行配置的实例。
				TcpServer.create()
						//配置TCP服务器主机
						.host("localhost")
						//配置TCP服务器端口
						.port(8080)
						//	以阻塞方式启动服务器并等待它完成初始化。
				         .bindNow(); 

		server.onDispose()
		      .block();
	}
}