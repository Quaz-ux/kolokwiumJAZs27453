package com.example.jazs27453nbp.controller;

import com.example.jazs27453nbp.models.ExchangeRate;
import com.example.jazs27453nbp.service.ExchangeRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/exchange-rate")
public class ExchangeRateController {

    private final ExchangeRateService service;

    public ExchangeRateController(ExchangeRateService service) {
        this.service = service;
    }

    @Operation(summary = "Get avg exchange rate", description = "Retunrs avg exchange rate of currency and date range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping
    public ResponseEntity<ExchangeRate> getAverageExchangeRate(
            @RequestParam String currency,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        ExchangeRate rate = service.getAverageExchangeRate(currency, startDate, endDate);
        return ResponseEntity.ok(rate);
    }
}

