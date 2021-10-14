package com.example.fooney;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Recipe implements Serializable {
    public enum Category {
        Alle,
        Günstig,
        Frühstück,
        Mittagessen,
        Snacks,
        Desserts,
        Vegan
    }

    @PrimaryKey(autoGenerate = true)
    private long uid;

    private String name;
    private String zutaten;
    private String beschreibung;
    private String imageUrl;
    private int category;

    @Ignore
    public Recipe(String name, String zutaten, String beschreibung, String imageUrl, Category category)
    {
        setName(name);
        setZutaten(zutaten);
        setBeschreibung(beschreibung);
        setImageUrl(imageUrl);
        setCategoryAsEnumField(category);
    }

    public Recipe(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setZutaten(String zutaten) {
        this.zutaten = zutaten;
    }

    public String getZutaten() {
        return zutaten;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getCategory(){
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setCategoryAsEnumField(Category category)
    {
        this.category = category.ordinal();
    }

    public Category getCategoryAsEnumField()
    {
        return Category.values()[category];
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
