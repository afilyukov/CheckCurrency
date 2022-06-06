package ru.afilyukov.CheckCurrency.Client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Openexchangerates API interface
 */

@FeignClient(url = "${exchange.api.path}", name = "${exchange.feign.client.name}")
public interface ExchangeClient {

    /**
     * Request method to get today's exchange rate
     */
    @GetMapping("${exchange.api.path.latest}")
    JsonNode getTodayRatesRequest(@RequestParam(name = "app_id") String appId,
                                         @RequestParam String base);

    /**
     * Query method to get yesterday's exchange rate
     */
    @GetMapping("${exchange.api.path.historical}/{yesterdayDate}.json")
    JsonNode getYesterdayRatesRequest(@RequestParam(name = "app_id") String appId,
                                             @RequestParam String base,
                                             @PathVariable("yesterdayDate") String Date);
}
