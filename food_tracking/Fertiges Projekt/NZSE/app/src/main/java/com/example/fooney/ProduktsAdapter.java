package com.example.fooney;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProduktsAdapter extends RecyclerView.Adapter<ProduktsAdapter.ProductsViewHolder> {
    private List<Produkt> products;
    private Context context;

    public ProduktsAdapter(Context context, List<Produkt> produkts) {
        this.context = context;
        this.products = produkts;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_viewholder_product, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Produkt produkt = products.get(position);

        holder.textViewTitle.setText(String.format("%s", produkt.getM_name()));
        holder.textViewMenge.setText(String.format("%s", produkt.getM_anzahl()));
        holder.textViewMHD.setText(String.format("%s", produkt.getM_datum()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTitle;// Produktname;
        TextView textViewMenge;// Verfügbare Anzahl
        TextView textViewMHD; //Mindesthalbarkeitsdatum

        //Hier Zuweisung
        public ProductsViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewMenge = itemView.findViewById(R.id.textViewMenge);
            textViewMHD = itemView.findViewById(R.id.textViewMHD);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Produkt produkt = products.get(getAdapterPosition());

            Intent intent = new Intent(context, get_product.class);
            intent.putExtra("produkt", produkt);

            ((Activity) context).startActivity(intent);
        }

    }
}


