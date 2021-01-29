package moneycalculator.persistence.file;

import moneycalculator.model.Currency;
import moneycalculator.persistence.CurrencyListLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileCurrencyListLoader implements CurrencyListLoader {
    private final String fileName;

    public FileCurrencyListLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Currency> load() {
        List<Currency> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                result.add(new Currency(split[0], split[1], split[2]));
            }
            return result;
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
