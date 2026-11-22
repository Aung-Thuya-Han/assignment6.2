package model;

import java.util.List;

public class CurrencyModel {
    private final List<Currency> currencies = List.of(
            new Currency("EUR", "Euro", 1.0),
            new Currency("USD", "US dollar", 1.0990),
            new Currency("GBP", "Pound sterling", 0.83735)
    );

    public List<Currency> getCurrencies() {
        return currencies;
    }
}
