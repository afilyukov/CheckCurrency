package ru.afilyukov.CheckCurrency.JsonParsers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

/**
 * Parser for processing json received from openexchangerates API
 */
@Component
public class CurrencyRatesParser {
    /**
    * Getting a currency exchange rate method
    */
    public double getCurrency(JsonNode node, String comparedCurrency) {
        if (node.has("rates")) {
            if (node.get("rates").has(comparedCurrency)) {
                return Double.parseDouble(node.get("rates").get(comparedCurrency).toString());
            }
        }
        return 0;
    }

    /**
    * Getting all currency exchange rates method
    */
    public List<String> getAllCurrencies(JsonNode node){
        List<String> listOfCurrencies = new ArrayList<>();
        Iterator<String> iteratorFieldName = node.get("rates").fieldNames();
        while(iteratorFieldName.hasNext()) {
            listOfCurrencies.add(iteratorFieldName.next());
        }
        return listOfCurrencies;
    }
}
