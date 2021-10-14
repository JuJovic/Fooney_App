package com.example.fooney;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Date;

public class bestand_recyclerview extends AppCompatActivity {

    private List<Produkt> allProducts;
    private RecyclerView recyclerView;
    private float sum_prices = 0;
    AppDatabase db;
    Button backToMenu;
    TextView produktsumPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestand_recyclerview);

        backToMenu = findViewById(R.id.btnBackToMenu);
        recyclerView = findViewById(R.id.rvproducts);
        produktsumPrice = findViewById(R.id.sum_price);
        allProducts = JJDatabaseClient.getInstance(getApplicationContext()).getAppDatabase().produktDao().getAll();
        for (int i = 0; i < allProducts.size(); i++) {
            sum_prices += allProducts.get(i).getM_price();
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ProduktsAdapter(this, allProducts));
        produktsumPrice.setText(sum_prices + " Euro");
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(bestand_recyclerview.this, JJMainActivity.class));
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void aktualisieren()
    {
        allProducts = JJDatabaseClient.getInstance(getApplicationContext()).getAppDatabase().produktDao().getAll();
        recyclerView.setAdapter(new ProduktsAdapter(this, allProducts));
        Collections.sort(allProducts, compareDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume(){
        super.onResume();
        aktualisieren();
    }

    Comparator<Produkt> compareDate = new Comparator<Produkt>() {
        @Override
        public int compare(Produkt o1, Produkt o2) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = null;
            Date date2 = null;
            try{
                date1 = sdf.parse(o1.getM_datum());
                date2 = sdf.parse(o2.getM_datum());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if((date1 != null) && (date2 != null))
            {
                return date1.compareTo(date2);
            } else {
                return 0;
            }


        }
    };
}

