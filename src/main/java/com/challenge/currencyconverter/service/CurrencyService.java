package com.challenge.currencyconverter.service;

import com.challenge.currencyconverter.model.ConvertRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyService {

    private final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public Map<String, Double> fetchRates(String base) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = API_URL + base;
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            // Handle response and map to correct types
            Map<String, Double> rates = new HashMap<>();
            if (response != null && response.containsKey("rates")) {
                Map<String, Object> rateMap = (Map<String, Object>) response.get("rates");

                for (Map.Entry<String, Object> entry : rateMap.entrySet()) {
                    // Ensure all values are treated as Double
                    Double rateValue = (entry.getValue() instanceof Integer) ?
                            ((Integer) entry.getValue()).doubleValue() :
                            (Double) entry.getValue();

                    rates.put(entry.getKey(), rateValue);
                }
            }

            return rates;
        } catch (RestClientException ex) {
            throw new IllegalArgumentException("Failed to fetch exchange rates: " + ex.getMessage());
        }
    }

    public Map<String, Object> convertCurrency(ConvertRequest request) {
        Map<String, Double> rates = fetchRates(request.getFrom());
        double rate = rates.getOrDefault(request.getTo(), 0.0);

        if (rate == 0.0) {
            throw new IllegalArgumentException("Invalid currency code: " + request.getTo());
        }

        double convertedAmount = request.getAmount() * rate;
        Map<String, Object> result = new HashMap<>();
        result.put("from", request.getFrom());
        result.put("to", request.getTo());
        result.put("amount", request.getAmount());
        result.put("convertedAmount", convertedAmount);
        result.put("rate", rate);  // Add the conversion rate used

        return result;
    }
}
