package com.example.jazs27453nbp.service;

import com.example.jazs27453nbp.client.NbpClient;
import com.example.jazs27453nbp.models.ExchangeRate;
import com.example.jazs27453nbp.models.NbpResponse;
import com.example.jazs27453nbp.repository.ExchangeRateRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExchangeRateService {

    private final ExchangeRateRepository repository;
    private final NbpClient nbpClient;

    public ExchangeRateService(ExchangeRateRepository repository, NbpClient nbpClient) {
        this.repository = repository;
        this.nbpClient = nbpClient;
    }

    public double calculateAverageRate(List<NbpResponse.Rate> rates) {
        return rates.stream().mapToDouble(NbpResponse.Rate::getMid).average().orElse(0);
    }

    public ExchangeRate getAverageExchangeRate(String currency, LocalDate startDate, LocalDate endDate) {
        NbpResponse response = nbpClient.getExchangeRatesPerPeriod(currency, startDate.toString(), endDate.toString());
        double averageRate = calculateAverageRate(response.getRates());

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setCurrency(currency);
        exchangeRate.setStartDate(startDate);
        exchangeRate.setEndDate(endDate);
        exchangeRate.setAverageRate(averageRate);
        exchangeRate.setQueryTime(LocalDateTime.now());

        repository.save(exchangeRate);

        return exchangeRate;
    }
}
