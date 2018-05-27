package feign;

import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;

import java.util.Base64;

public class UserFeignClientBuilder {
    public UsersClient createUsersClient() {
        return createUsersClient("user", "password");
    }
    public UsersClient createUsersClient(String login, String password) {
        return Feign.builder()
                .client(new OkHttpClient())
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .requestInterceptor(requestTemplate -> {
                    byte[] bytes = Base64.getEncoder().encode((login + ":" + password).getBytes());
                    String bearer = new String(bytes);
                    requestTemplate.header("Authorization", "Basic " + bearer);
                })
                .target(UsersClient.class, "http://localhost:8080");
    }
}
