package com.meli.android.carddrawer.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.meli.android.carddrawer.app.model.MasterCardConfiguration;
import com.meli.android.carddrawer.app.model.VisaCardBlueConfiguration;
import com.meli.android.carddrawer.app.model.VisaCardGrayConfiguration;
import com.meli.android.carddrawer.app.model.VisaCardGreenConfiguration;
import com.meli.android.carddrawer.app.model.VisaCardRedConfiguration;
import com.meli.android.carddrawer.app.model.VisaCardYellowConfiguration;
import com.meli.android.carddrawer.app.util.ScaleUtil;
import com.meli.android.carddrawer.configuration.DefaultCardConfiguration;
import com.meli.android.carddrawer.model.Card;
import com.meli.android.carddrawer.model.CardDrawerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Card card;
    private CardDrawerView cardDrawerView;

    private Button buttonLarge;
    private Button buttonMedium;
    private Button buttonSmall;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ScaleUtil.isLowRes(this)) {
            setContentView(R.layout.card_drawer_app_activity_main_lowres);
        } else {
            setContentView(R.layout.card_drawer_app_activity_main);
        }
        cardDrawerView = findViewById(R.id.card_header_container);
        buttonLarge = findViewById(R.id.button_large);
        buttonMedium = findViewById(R.id.button_medium);
        buttonSmall = findViewById(R.id.button_small);
        card = cardDrawerView.getCard();
        initButtonsEvents();
        initCardConfigurationOptions();
        initCardNumber();
        initCardName();
        initExpirationDate();
        initSecurityCode();
    }

    private void initButtonsEvents() {
        buttonLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAndShowCard(R.id.card_header_container);
            }
        });

        buttonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAndShowCard(R.id.card_header_container_medium);
            }
        });

        buttonSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAndShowCard(R.id.card_header_container_small);
            }
        });
    }

    private void hideAndShowCard(int resource) {
        cardDrawerView.setVisibility(View.GONE);
        cardDrawerView = findViewById(resource);
        cardDrawerView.setVisibility(View.VISIBLE);
        card = cardDrawerView.getCard();
    }

    private void initSecurityCode() {
        TextView securityCode = findViewById(R.id.security_code);

        securityCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cardDrawerView.showSecurityCode();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                card.setSecCode(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    cardDrawerView.show();
                }
            }
        });
    }

    private void initExpirationDate() {
        TextView expirationDate = findViewById(R.id.expiration_date);

        expirationDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                card.setExpiration(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Do nothing
            }
        });
    }

    private void initCardName() {
        TextView cardName = findViewById(R.id.card_name);

        cardName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                card.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Do nothing
            }
        });

        cardName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDrawerView.show();
            }
        });
    }

    private void initCardNumber() {
        TextView cardNumber = findViewById(R.id.card_number);

        cardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                card.setNumber(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Do nothing
            }
        });

        cardNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDrawerView.show();
            }
        });
    }

    private void initCardConfigurationOptions() {
        Spinner cardsSpinner = findViewById(R.id.spinner_cards);

        final List<CardConfigurationOption> cardOptions = new ArrayList<>();

        DefaultCardConfiguration defaultCardConfiguration = new DefaultCardConfiguration(this);
        cardOptions.add(new CardConfigurationOption(defaultCardConfiguration, "Default"));

        VisaCardBlueConfiguration visaCardBlueConfiguration = new VisaCardBlueConfiguration(this);
        cardOptions.add(new CardConfigurationOption(visaCardBlueConfiguration, "Visa blue"));

        VisaCardGreenConfiguration visaCardGreenConfiguration = new VisaCardGreenConfiguration(this);
        cardOptions.add(new CardConfigurationOption(visaCardGreenConfiguration, "Visa green"));

        VisaCardRedConfiguration visaCardRedConfiguration = new VisaCardRedConfiguration(this);
        cardOptions.add(new CardConfigurationOption(visaCardRedConfiguration, "Visa red"));

        VisaCardGrayConfiguration visaCardGrayConfiguration = new VisaCardGrayConfiguration(this);
        cardOptions.add(new CardConfigurationOption(visaCardGrayConfiguration, "Visa gray"));

        VisaCardYellowConfiguration visaCardYellowConfiguration = new VisaCardYellowConfiguration(this);
        cardOptions.add(new CardConfigurationOption(visaCardYellowConfiguration, "Visa yellow"));

        MasterCardConfiguration masterCardConfiguration = new MasterCardConfiguration(this);
        cardOptions.add(new CardConfigurationOption(masterCardConfiguration, "Master"));

        ArrayAdapter<CardConfigurationOption> cardAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cardOptions);
        cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cardsSpinner.setAdapter(cardAdapter);
        cardsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CardConfigurationOption selection = cardOptions.get(position);
                cardDrawerView.show(selection.getCardConfiguration());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });
    }
}
