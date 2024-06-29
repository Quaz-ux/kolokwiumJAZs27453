package com.example.jazs27453nbp.client;

import com.example.jazs27453nbp.models.NbpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NbpClient {
    private final String baseUrl = "http://api.nbp.pl/api/exchangerates/rates/a/";
    private final RestTemplate restTemplate;

    public NbpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public NbpResponse getExchangeRatesPerPeriod(String currencyCode, String startDate, String endDate) {
        String url = String.format("%s%s/%s/%s/", baseUrl, currencyCode, startDate, endDate);
        return restTemplate.getForObject(url, NbpResponse.class);
    }
}
