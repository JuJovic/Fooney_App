package com.example.fooney;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailsActivity extends AppCompatActivity {

    private Recipe recipe = null;
    AppDatabase db;
    private EditText editTitle, editBeschreibung, editZutaten;
    private ImageView imageViewRecipe;
    private Spinner spinner;
    private static final int REQUEST_PICK_IMAGE = 42;
    private String imageUri = "";

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipedetails);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTitle = findViewById(R.id.editTitle);
        editZutaten = findViewById(R.id.editZutaten);
        editBeschreibung = findViewById(R.id.editBeschreibung);

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setVisibility(View.INVISIBLE);
        Button btnUpdate = findViewById(R.id.btnSave);
        btnUpdate.setOnClickListener(view -> saveItem());
        spinner = findViewById(R.id.spinnerCategories);
        spinner.setAdapter(new ArrayAdapter<Recipe.Category>(this, R.layout.spinner_item, Recipe.Category.values()));
        spinner.setSelected(false);
        imageViewRecipe = findViewById(R.id.imageView);
        imageViewRecipe.setOnClickListener(view -> selectImage());

        recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        if (recipe != null){
            editZutaten.setText(recipe.getZutaten());
            editBeschreibung.setText(recipe.getBeschreibung());
            editTitle.setText(recipe.getName());
            imageUri = recipe.getImageUrl();
            Uri uri = Uri.parse(imageUri);
            if(uri != null)
                imageViewRecipe.setImageURI(uri);
            btnDelete.setVisibility(View.VISIBLE);
            btnDelete.setOnClickListener(view -> deleteItem());
            spinner.setSelected(true);
            spinner.setSelection(recipe.getCategory());
        }
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
    }

    private void deleteItem()
    {
        if(recipe != null){
            new Thread(() ->{
                db.recipeDao().delete(recipe);
                RecipeDetailsActivity.this.runOnUiThread(() -> Toast.makeText(RecipeDetailsActivity.this, "gelöscht", Toast.LENGTH_LONG).show());

            }).start();
        }
        finish();
    }

    private void saveItem()
    {
        if(recipe == null)
        {
            recipe = new Recipe();
        }
        recipe.setName(editTitle.getText().toString().trim());
        recipe.setZutaten(editZutaten.getText().toString().trim());
        recipe.setBeschreibung(editBeschreibung.getText().toString().trim());
        recipe.setCategory(spinner.getSelectedItemPosition());
        recipe.setImageUrl(imageUri);
        if(recipe.getUid() > 0)
            new Thread(() -> {db.recipeDao().update(recipe);}).start();
        else
            new Thread(() -> {db.recipeDao().insert(recipe);}).start();
        finish();
    }

    private void selectImage(){
        Intent gallery = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, REQUEST_PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        if(resultCode == RESULT_OK && requestCode == REQUEST_PICK_IMAGE && data != null){
            Uri uri = data.getData();
            if(uri != null){
                getContentResolver().takePersistableUriPermission(uri,Intent.FLAG_GRANT_READ_URI_PERMISSION);
                imageViewRecipe.setImageURI(uri);
                imageUri = uri.toString();
            }
        }
    }
}
