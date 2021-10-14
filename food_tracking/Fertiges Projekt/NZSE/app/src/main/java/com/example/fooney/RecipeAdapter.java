package com.example.fooney;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipes;
    private Context context;

    public RecipeAdapter(Context context, List<Recipe> recipes)
    {
        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_recipeblock, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.textViewTitle.setText(String.format("%s (%s)", recipe.getName(),recipe.getCategoryAsEnumField().toString()));
        holder.imageViewRecipe.setImageURI(Uri.parse(recipe.getImageUrl()));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTitle;
        ImageView imageViewRecipe;

        public RecipeViewHolder(View itemView){
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            imageViewRecipe = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            Recipe recipe = recipes.get(getAdapterPosition());

            Intent intent = new Intent(context, RecipeDetailsActivity.class);
            intent.putExtra("recipe", recipe);

            ((Activity) context).startActivity(intent);
        }
    }
}

