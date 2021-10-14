package com.example.fooney;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Produkt.class}, version = 1)
public abstract class JJAppDatabase extends RoomDatabase {
    public abstract ProduktDao produktDao();

}