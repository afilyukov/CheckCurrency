package ru.afilyukov.CheckCurrency.Services;

import ru.afilyukov.CheckCurrency.Client.ExchangeClient;
import ru.afilyukov.CheckCurrency.JsonParsers.CurrencyRatesParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;

/**
 * Service to work with currency exchange rates
 */
@Service
public class CurrencyRatesService {
    private final ExchangeClient exchangeClient;
    private final CurrencyRatesParser currencyRatesParser;
    @Value("${exchange.api.id}")
    private String appId;
    @Value("${exchange.base}")
    private String base;
    private List<String> listCurrency = null;

    public CurrencyRatesService(ExchangeClient exchangeClient, CurrencyRatesParser currencyRatesParser) {
        this.exchangeClient = exchangeClient;
        this.currencyRatesParser = currencyRatesParser;
    }

    /**
     * Method to get the difference between today's rate and yesterday's rate
     */
    public double getMoney(String desCurrency) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        JsonNode todayCurrRates = exchangeClient.getTodayRatesRequest(appId, base);
        JsonNode yesterdayCurrRates = exchangeClient.getYesterdayRatesRequest(appId, base, yesterday.toString());

        if (todayCurrRates == null || yesterdayCurrRates == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "This currency API not found");

        if (listCurrency == null)
            listCurrency = currencyRatesParser.getAllCurrencies(todayCurrRates);

        if (!listCurrency.contains(desCurrency))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This currency does not exist");

        double todayCurrency = currencyRatesParser.getCurrency(todayCurrRates, desCurrency);
        double yesterdayCurrency = currencyRatesParser.getCurrency(yesterdayCurrRates, desCurrency);
        currencyRatesParser.getAllCurrencies(todayCurrRates);
        return todayCurrency - yesterdayCurrency;
    }
}
