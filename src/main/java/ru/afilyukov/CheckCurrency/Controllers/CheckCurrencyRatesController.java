package ru.afilyukov.CheckCurrency.Controllers;

import ru.afilyukov.CheckCurrency.Services.GifService;
import ru.afilyukov.CheckCurrency.Services.CurrencyRatesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Check currency controller
 */
@RestController
@RequestMapping("/check-service")
public class CheckCurrencyRatesController {
    final
    GifService gifService;
    final
    CurrencyRatesService currencyRatesService;
    @Value("${gyphi.api.search.rich}")
    private String richKey;
    @Value("${gyphi.api.search.broke}")
    private String brokeKey;
    @Value("${gyphi.api.search.nothing}")
    private String nothingKey;
    double diffCurrency = 0;

    public CheckCurrencyRatesController(GifService gifService, CurrencyRatesService currencyRatesService) {
        this.gifService = gifService;
        this.currencyRatesService = currencyRatesService;
    }

    /**
     * Endpoint to display a gif according to the growth/fall of the currency rate
     */
    @GetMapping("/currency")
    public String getCurrency(@RequestParam(name = "cmpr") String cmpr) {
        diffCurrency = currencyRatesService.getMoney(cmpr);
        String gifUrlTag = "<img src=%s/>";
        if (diffCurrency > 0) {
            return String.format(gifUrlTag, gifService.getGif(richKey));
        } else if (diffCurrency < 0) {
            return String.format(gifUrlTag, gifService.getGif(brokeKey));
        } else {
            return String.format(gifUrlTag, gifService.getGif(nothingKey));
        }
    }
}
