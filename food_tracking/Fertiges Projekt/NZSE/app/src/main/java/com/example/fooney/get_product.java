package com.example.fooney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class get_product extends AppCompatActivity { //Serielizeable

    String name;
    String mhd;
    String anzahl;
    String gewicht;
    String price;
    private Produkt produkt = null;
    private Button cancel;
    private Button delete;
    private Button edit;
    JJAppDatabase db;
    private Context context;
    TextView produktName, produktAnzahl, produktGewicht, produktMhd, produktPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_product);

        delete = findViewById(R.id.btnDelete);
        edit = findViewById(R.id.btnEditProdukt);
        cancel = findViewById(R.id.btnCancel2);

        produkt = (Produkt) getIntent().getSerializableExtra("produkt");
        db = JJDatabaseClient.getInstance(getApplicationContext()).getAppDatabase();

        //name = intent.getStringExtra("PRODUKT_NAME");
        //mhd = intent.getStringExtra("MHD");
        //anzahl = intent.getIntExtra("ANZAHL", 0);
        //gewicht = intent.getIntExtra("GEWICHT", 0);

        name = produkt.getM_name();
        anzahl = String.valueOf(produkt.getM_anzahl());
        gewicht = String.valueOf(produkt.getM_gewicht());
        price = String.valueOf(produkt.getM_price());
        mhd = produkt.getM_datum();

        produktName = findViewById(R.id.produktNameTextView);
        produktAnzahl = findViewById(R.id.produktAnzahlTextView);
        produktGewicht = findViewById(R.id.produktGewichtTextView);
        produktPrice = findViewById(R.id.produktPriceTextView);
        produktMhd = findViewById(R.id.produktMhdTextView);

        produktName.setText(name);
        produktAnzahl.setText(anzahl);
        produktGewicht.setText(gewicht + " g");
        produktPrice.setText(price + " Euro");
        produktMhd.setText(mhd);

        delete.setOnClickListener(view -> deleteProdukt());

        edit.setOnClickListener(view -> {
            Intent intent = new Intent(this, add_product.class);
            intent.putExtra("produkt", produkt);
            startActivity(intent);
            finish();
        });

        cancel.setOnClickListener(view -> finish());
    }

    private void deleteProdukt(){
        if (produkt != null){
            db.produktDao().delete(produkt);
            Toast.makeText(this, "GELÖSCHT", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "LÖSCHEN NICHT MÖGLICH", Toast.LENGTH_LONG).show();
        }
        finish();
    }
}
