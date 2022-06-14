package com.lmarques.mystocktrader.cmc;

import com.lmarques.mystocktrader.cmc.model.CMCResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.function.Supplier;

@Component
public class HttpClientTemplate {

    @Value("${cmc.api.key}")
    private String cmcApiKey;
    private String baseURL = "https://pro-api.coinmarketcap.com/v1/";

    public CMCResponse request(String path, Optional<String> queryParams) throws IOException, InterruptedException {

        if(cmcApiKey == null)
            return new CMCResponse();

        HttpClient client = HttpClient.newHttpClient();

        if(queryParams.isPresent()){
            path.concat("?id=").concat(queryParams.get());
        }

        HttpRequest request = HttpRequest.newBuilder(URI.create(baseURL.concat(path)))
                .header("accept", "application/json")
                .header("X-CMC_PRO_API_KEY", cmcApiKey)
                .build();



        HttpResponse<Supplier<CMCResponse>> response = client.send(request, new JsonBodyHandler<>(CMCResponse.class));

        return response.body().get();
    }
}
