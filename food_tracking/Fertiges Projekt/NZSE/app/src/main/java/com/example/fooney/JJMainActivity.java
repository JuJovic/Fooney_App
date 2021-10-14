package com.example.fooney;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class JJMainActivity extends AppCompatActivity {

    private List<Produkt> allProducts = null;
    Button buttonBestand;
    Button buttonhinzufuegen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jjactivity_main);

        buttonBestand = findViewById(R.id.buttonBestand);
        buttonhinzufuegen = findViewById(R.id.btnAdd);

        buttonBestand.setOnClickListener(view-> {
            Intent intent = new Intent(this, bestand_recyclerview.class);
            startActivity(intent);
        });

        buttonhinzufuegen.setOnClickListener(view-> {
            Intent intent = new Intent(this, add_product.class);
            startActivity(intent);

        });

        /*SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date today = new Date();
        dateFormat.format(today);*/
       /* AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "Bestand").build();*/
        //allProducts = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().produktDao().getAll();
        // RecyclerView  recyclerview = findViewById(R.id.recyclerview);
        //recyclerview.setAdapter(new ProduktsAdapter(this, allProducts));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }



}
