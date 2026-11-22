package controller;

import model.Currency;
import view.CurrencyConverterView;

import java.util.Locale;

public class CurrencyConverterController {
    private final CurrencyConverterView view;

    public CurrencyConverterController(CurrencyConverterView view) {
        this.view = view;
    }

    public void convert() {
        Currency from = view.getSourceCurrency();
        Currency to = view.getTargetCurrency();
        if (from == null || to == null) {
            view.showError("Choose both currencies.");
            return;
        }

        double amount;
        try {
            String input = view.getAmount().trim().replace(',', '.');
            amount = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            view.showError("Enter a number, for example 12.50.");
            return;
        }

        if (!Double.isFinite(amount) || amount < 0) {
            view.showError("Enter a valid amount of zero or more.");
            return;
        }

        double amountInEuros = amount / from.getUnitsPerEuro();
        double result = amountInEuros * to.getUnitsPerEuro();
        if (!Double.isFinite(result)) {
            view.showError("The amount is too large. Enter a smaller amount.");
            return;
        }
        view.showResult(String.format(Locale.ROOT, "%.2f", result));
    }
}
