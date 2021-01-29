package moneycalculator.persistence;

import moneycalculator.model.Currency;

import java.util.List;

public interface CurrencyListLoader {
    List<Currency> load();
}
