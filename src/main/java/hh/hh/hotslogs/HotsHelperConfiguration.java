package hh.hh.hotslogs;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Feign;
import feign.okhttp.OkHttpClient;
import hh.hh.hotslogs.HotslogsService.Hotslogs;
import hh.hh.hotslogs.HotslogsService.HotslogsApi;

@Configuration
public class HotsHelperConfiguration {

	private static final String PROXY_URL = "10.206.246.20";
	private static final int PROXY_PORT = 8080;
	private static final String HOTSLOGS_URL = "http://www.hotslogs.com/";
	private static final String HOTSLOGSAPI_URL = "https://api.hotslogs.com/Public/";

	@Bean
	public Hotslogs getHotsLogs() {
		okhttp3.OkHttpClient delegate = new okhttp3.OkHttpClient.Builder()
				.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_URL, PROXY_PORT))).build();
		return Feign.builder().client(new OkHttpClient(delegate)).target(Hotslogs.class, HOTSLOGS_URL);
	}

	@Bean
	public HotslogsApi getHotsLogsApi() {
		okhttp3.OkHttpClient delegate = new okhttp3.OkHttpClient.Builder()
				.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_URL, PROXY_PORT))).build();
		return Feign.builder().client(new OkHttpClient(delegate)).target(HotslogsApi.class, HOTSLOGSAPI_URL);
	}

}
