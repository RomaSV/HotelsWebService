package feign;

import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;

import java.util.Base64;

public class HotelFeignClientBuilder {
    public HotelsClient createHotelsClient() {
        return createHotelsClient("user", "password");
    }
    public HotelsClient createHotelsClient(String login, String password) {
        return Feign.builder()
            .client(new OkHttpClient())
            .decoder(new GsonDecoder())
            .encoder(new GsonEncoder())
            .requestInterceptor(requestTemplate -> {
                byte[] bytes = Base64.getEncoder().encode((login + ":" + password).getBytes());
                String bearer = new String(bytes);
                requestTemplate.header("Authorization", "Basic " + bearer);
            })
            .target(HotelsClient.class, "http://localhost:8080");
    }
}
