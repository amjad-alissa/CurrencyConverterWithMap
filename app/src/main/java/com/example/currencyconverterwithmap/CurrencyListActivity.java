package com.example.currencyconverterwithmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

public class CurrencyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_currency_list);
        ListView listViewCurrencies = findViewById(R.id.listViewCurrency);

        final ExchangeRateDatabase exchangeRateDatabase = new ExchangeRateDatabase();

        ExchangeRate[] RATES = {
                new ExchangeRate("EUR", "Bruxelles", 1.0),
                new ExchangeRate("USD", "Washington", 1.0845),
                new ExchangeRate("JPY", "Tokyo", 130.02),
                new ExchangeRate("BGN", "Sofia", 1.9558),
                new ExchangeRate("CZK", "Prague", 27.473),
                new ExchangeRate("DKK", "Copenhagen", 7.4690),
                new ExchangeRate("GBP", "London", 0.73280),
                new ExchangeRate("HUF", "Budapest", 299.83),
                new ExchangeRate("PLN", "Warsaw", 4.0938),
                new ExchangeRate("RON", "Bucharest", 4.4050),
                new ExchangeRate("SEK", "Stockholm", 9.3207),
                new ExchangeRate("CHF", "Bern", 1.0439),
                new ExchangeRate("NOK", "Oslo", 8.6545),
                new ExchangeRate("HRK", "Zagreb", 7.6448),
                new ExchangeRate("RUB", "Moscow", 62.5595),
                new ExchangeRate("TRY", "Ankara", 2.8265),
                new ExchangeRate("AUD", "Canberra", 1.4158),
                new ExchangeRate("BRL", "Brasilia", 3.5616),
                new ExchangeRate("CAD", "Ottawa", 1.3709),
                new ExchangeRate("CNY", "Beijing", 6.7324),
                new ExchangeRate("HKD", "Hong Kong", 8.4100),
                new ExchangeRate("IDR", "Jakarta", 14172.71),
                new ExchangeRate("ILS", "Jerusalem", 4.3019),
                new ExchangeRate("INR", "New Delhi", 67.9180),
                new ExchangeRate("KRW", "Seoul", 1201.04),
                new ExchangeRate("MXN", "Mexico City", 16.5321),
                new ExchangeRate("MYR", "Kuala Lumpur", 4.0246),
                new ExchangeRate("NZD", "Wellington", 1.4417),
                new ExchangeRate("PHP", "Manila", 48.527),
                new ExchangeRate("SGD", "Singapore", 1.4898),
                new ExchangeRate("THB", "Bangkok", 35.328),
                new ExchangeRate("ZAR", "Cape Town", 13.1446)
        };

        CurrencyListAdapter currencyListAdapter = new CurrencyListAdapter(Arrays.asList(RATES));

        listViewCurrencies.setAdapter(currencyListAdapter);

        listViewCurrencies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TextView currencyNameTextview = view.findViewById(R.id.textViewCurrencyName); // TODO better name?
                String currencyName = currencyNameTextview.getText().toString();
//                Log.i(currencyName, currencyName);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0`?q="
                        + exchangeRateDatabase.getCapital(currencyName)));
                startActivity(intent); // TODO What does mean "Can't search" ? Was solved itselves.
            }
        });
    }
}

