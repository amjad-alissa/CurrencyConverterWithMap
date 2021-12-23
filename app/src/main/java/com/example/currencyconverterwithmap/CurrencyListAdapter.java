package com.example.currencyconverterwithmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class CurrencyListAdapter extends BaseAdapter {

    private List<ExchangeRate> currenciesList;

    public CurrencyListAdapter(List<ExchangeRate> data) {

        this.currenciesList = data;
    }
    @Override
    public int getCount() {

        return currenciesList.size();
    }

    @Override
    public Object getItem(int position) {

        return currenciesList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();
        ExchangeRate exchangeRate = currenciesList.get(position);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.spinner_element, null, false);
        }

        int imageId = context.getResources().getIdentifier(
                "flag_" + exchangeRate.getCurrencyName().toLowerCase(), "drawable", context.getPackageName());
        ImageView imageView = convertView.findViewById(R.id.imageViewFlag);
        imageView.setImageResource(imageId);

        TextView currencyName = convertView.findViewById(R.id.textViewCurrencyName);
        currencyName.setText(exchangeRate.getCurrencyName());

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        TextView currencyExchangeRate = convertView.findViewById(R.id.textViewExchangeRate);
        currencyExchangeRate.setText(decimalFormat.format(exchangeRate.getRateForOneEuro()));

        return convertView;
    }
}
