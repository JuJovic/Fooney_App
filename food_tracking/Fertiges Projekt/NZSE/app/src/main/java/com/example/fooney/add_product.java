package com.example.fooney;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class add_product extends AppCompatActivity { //Serilizable

    //public static final String TAG = "CreateUser";

    DatePickerDialog picker;
    EditText product;
    EditText number;
    EditText datum;
    EditText weight;
    EditText price;
    Button button_save;
    Button cancel;
    Button increment1;
    Button increment2;
    Button decrement1;
    Button decrement2;
    JJAppDatabase db; //merken!!
    private Produkt produkt = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        product = findViewById(R.id.editTextProduktname);
        number = findViewById(R.id.editTextAnzahl);
        datum = findViewById(R.id.editTextMHD);
        weight = findViewById(R.id.editTextGewicht);
        price = findViewById(R.id.editTextPrice);
        button_save = findViewById(R.id.btnSave);
        cancel = findViewById(R.id.btnCancel);
        increment1 = findViewById(R.id.btnIncrement);
        increment2 = findViewById(R.id.btnIncrement2);
        decrement1 = findViewById(R.id.btnDecrement);
        decrement2 = findViewById(R.id.btnDecrement2);
        datum.setInputType(InputType.TYPE_NULL);
//        setContentView(R.layout.produkt_hinzufuegen);
//
//        product = findViewById(R.id.prod);
//        number = findViewById(R.id.num);
//        datum = findViewById(R.id.dat);
//        weight = findViewById(R.id.weight);
//        datum.setInputType(InputType.TYPE_NULL);

        produkt = (Produkt) getIntent().getSerializableExtra("produkt");
        if(produkt != null) {
            product.setText(produkt.getM_name());
            number.setText(String.valueOf(produkt.getM_anzahl()));
            datum.setText(String.valueOf(produkt.getM_datum()));
            weight.setText(String.valueOf(produkt.getM_gewicht()));
            price.setText(String.valueOf(produkt.getM_price()));
        }

        db =  JJDatabaseClient.getInstance(getApplicationContext()).getAppDatabase();

        datum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(add_product.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                datum.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        //button = findViewById(R.id.button);

        //TODO testweise auskommentiert
        /*final JJAppDatabase db = Room.databaseBuilder(getApplicationContext(), JJAppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();*/

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO save to database
                //Log.d("day, month, year ", day, month, year);
                //Produkt produkt = new Users(product.getText().toString(), number.getText().toString(), datum.getText().toString());

                if (produkt == null)
                {
                    produkt = new Produkt();
                }

                produkt.setM_name(product.getText().toString());
                produkt.setM_anzahl(Integer.parseInt(number.getText().toString()));
                produkt.setM_gewicht(Float.parseFloat(weight.getText().toString()));
                produkt.setM_price(Float.parseFloat(price.getText().toString()));
                produkt.setM_datum(datum.getText().toString());

                if(produkt.getPid() > 0) {
                    db.produktDao().update(produkt);
                    Toast.makeText(add_product.this, "AKTUALISIERT", Toast.LENGTH_LONG).show();
                }
                else {
                    //Produkt produkt = new Produkt(product.getText().toString(), Integer.parseInt(number.getText().toString()), Float.parseFloat(weight.getText().toString()), datum.getText().toString());//db.userDao().insertAll(user);
                    db.produktDao().insert(produkt);
                    Toast.makeText(add_product.this, "HINZUGEFÜGT", Toast.LENGTH_LONG).show();

                }
                finish();
                //startActivity(new Intent(add_product.this, JJMainActivity.class));
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(add_product.this, JJMainActivity.class));
                finish();
            }
        });
        increment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int var;
                if(number.getText().toString().equals("")) {
                    var = 0;
                } else {
                    var = Integer.parseInt(number.getText().toString());
                }
                var++;
                number.setText(String.valueOf(var));
            }
        });
        increment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float var;
                if(weight.getText().toString().equals("")) {
                    var = 0;
                } else {
                    var = Float.parseFloat(weight.getText().toString());
                }
                var++;
                weight.setText(String.valueOf(var));
            }
        });
        decrement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int var;
                if(number.getText().toString().equals("")) {
                    var = 0;
                } else {
                    var = Integer.parseInt(number.getText().toString());
                }
                var--;
                number.setText(String.valueOf(var));
            }
        });
        decrement2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float var;
                if(weight.getText().toString().equals("")) {
                    var = 0;
                } else {
                    var = Float.parseFloat(weight.getText().toString());
                }
                var--;
                weight.setText(String.valueOf(var));
            }
        });
    }
}
