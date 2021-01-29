package moneycalculator.persistence.rest;

import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.persistence.ExchangeRateLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class RestExchangeRateLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            return new ExchangeRate(from, to, read(from.getCode(), to.getCode()));
        } catch (IOException ex) {
            return null;
        }
    }

    private double read(String from, String to) throws IOException {
        String line = read(new URL("https://free.currconv.com/api/v7/convert?q="+from+"_"+to+"&compact=ultra&apiKey=ddd381c0195c88cd0c0e"));
        Double exchangeRate=Double.parseDouble(line.substring(line.indexOf(to)+5,line.indexOf("}")));
        return exchangeRate;
    }

    private String read(URL url) throws IOException {
        InputStream is = url.openStream();
        byte[] buffer = new byte[1024];
        int length = is.read(buffer);
        return new String(buffer,0,length);
    }

}
