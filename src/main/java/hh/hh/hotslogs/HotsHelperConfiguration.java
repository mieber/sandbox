package hh.hh.hotslogs;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Feign;
import feign.okhttp.OkHttpClient;
import hh.hh.Conf;
import hh.hh.hotslogs.HotslogsService.Hotslogs;
import hh.hh.hotslogs.HotslogsService.HotslogsApi;

@Configuration
public class HotsHelperConfiguration {

	private static final String HOTSLOGS_URL = "http://www.hotslogs.com/";
	private static final String HOTSLOGSAPI_URL = "https://api.hotslogs.com/Public/";

	@Bean
	public Hotslogs getHotsLogs() {
		return Feign.builder().client(createClient()).target(Hotslogs.class, HOTSLOGS_URL);
	}

	@Bean
	public HotslogsApi getHotsLogsApi() {
		return Feign.builder().client(createClient()).target(HotslogsApi.class, HOTSLOGSAPI_URL);
	}

	private OkHttpClient createClient() {

		if (Conf.PROXY_ENABLED) {
			return new OkHttpClient(new okhttp3.OkHttpClient.Builder()
					.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Conf.PROXY_URL, Conf.PROXY_PORT))).build());
		}
		return new OkHttpClient();

	}

}
