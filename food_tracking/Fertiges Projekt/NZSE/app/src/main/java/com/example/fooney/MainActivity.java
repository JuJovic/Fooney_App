package com.example.fooney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<Recipe> allRecipes=null;
    private RecyclerView recyclerView;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvRecipes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        spinner = findViewById(R.id.spinnerCategories);
        spinner.setAdapter(new ArrayAdapter<Recipe.Category>(this,R.layout.spinner_item, Recipe.Category.values()));
        spinner.setOnItemSelectedListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view-> {
            Intent intent = new Intent(this, RecipeDetailsActivity.class);
            startActivity(intent);
        });
    }

    private void fillRecipes(final Recipe.Category category){
        List<Recipe> filteredRecipes;
        if(category == Recipe.Category.Alle)
            filteredRecipes = allRecipes;
        else{
            filteredRecipes = new ArrayList<Recipe>();
            allRecipes.stream().filter(c->c.getCategoryAsEnumField() == category).forEachOrdered(filteredRecipes::add);
        }
        recyclerView.setAdapter(new RecipeAdapter(this, filteredRecipes));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        fillRecipes(Recipe.Category.values()[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        spinner.setSelected(false);
        class LoadTask extends AsyncTask<Void, Void, List<Recipe>>{
            @Override
            protected List<Recipe> doInBackground(Void... voids){
                return DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().recipeDao().getAll();
            }

            @Override
            protected void onPostExecute(List<Recipe> recipes){
                allRecipes = recipes;
                fillRecipes(Recipe.Category.Alle);
            }
        }
        LoadTask loadTask = new LoadTask();
        loadTask.execute();
    }

    public void browser1(View view){
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.chefkoch.de/rezepte"));
        startActivity(browserIntent);
    }
}
