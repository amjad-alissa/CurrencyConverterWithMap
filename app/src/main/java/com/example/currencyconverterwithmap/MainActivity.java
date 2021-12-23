package com.example.currencyconverterwithmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView textViewFromValue;
    TextView textViewToValue;
    TextView textViewOutput;
    Spinner spinnerFromValue;
    Spinner spinnerToValue;
    EditText editTextInput;
    Button buttonCalculate;
    ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        //UIs texts
        textViewFromValue = findViewById(R.id.textViewFromValue);
        textViewFromValue.setText(R.string.text_view_from_defu);

        textViewToValue = findViewById(R.id.textViewToValue);
        textViewToValue.setText(R.string.text_view_to_defu);

        textViewOutput = findViewById(R.id.textViewOutput);
        textViewOutput.setText(R.string.text_view_output_defu);

        buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonCalculate.setText(R.string.button_calculate_defu);

        editTextInput = findViewById(R.id.editTextInput);

        //ExchangeRateDatabase
        final ExchangeRateDatabase exchangeRateDatabase = new ExchangeRateDatabase();

        /*//CurrencyListActivity
        String[] currencyList = exchangeRateDatabase.getCurrencies();

        //Adapter
       *//* ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_element,
                R.id.spinnerElement,
                currencyList);*/

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

        //Setting adapter to spinners
        spinnerFromValue = findViewById(R.id.spinnerFromValue);
        spinnerFromValue.setAdapter(currencyListAdapter);

        spinnerToValue = findViewById(R.id.spinnerToValue);
        spinnerToValue.setAdapter(currencyListAdapter);

        //Button event handler
        buttonCalculate.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ExchangeRate exchangeRateFrom = (ExchangeRate) spinnerFromValue.getSelectedItem();
                String fromValue = exchangeRateFrom.getCurrencyName();

//                String toValue = (String) spinnerToValue.getSelectedItem();
                ExchangeRate exchangeRateTo = (ExchangeRate) spinnerToValue.getSelectedItem();
                String toValue = exchangeRateTo.getCurrencyName();

                double input = Double.parseDouble(editTextInput.getText().toString());

                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                double output;

                //Two cases, because the exchange rate is calculated to 1 "EUR".
                // if the from or to value is 'EUR'
                if (fromValue.equals("EUR") || toValue.equals("EUR")) {
                    output = exchangeRateDatabase.convert(input, fromValue, toValue);
                    textViewOutput.setText(decimalFormat.format(output));
                }
                //the other case, if we convert from any currency to any other currency except "EUR"
                else {
                    output = exchangeRateDatabase.convert(input, fromValue, "EUR");
                    output = exchangeRateDatabase.convert(output, "EUR", toValue);
                    textViewOutput.setText(decimalFormat.format(output));
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem shareItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        String share = String.format("Currency Converter says: %s %s are %s %s",
                editTextInput.getText(), spinnerFromValue.getSelectedItem(),
                textViewOutput, spinnerToValue.getSelectedItem());
        setShareText(share);

        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.currenciesList:
                Intent intent = new Intent(MainActivity.this, CurrencyListActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_share:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void setShareText(String text) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        if (text != null) {
            shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        }
        shareActionProvider.setShareIntent(shareIntent);
    }
}
