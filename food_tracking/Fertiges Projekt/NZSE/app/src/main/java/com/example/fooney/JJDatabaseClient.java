package com.example.fooney;

import android.content.Context;

import androidx.room.Room;

public class JJDatabaseClient {
    public static final String DATABASE_NAME = "produkt_db";
    private static JJDatabaseClient instance;
    private Context context;
    private JJAppDatabase db;

    private JJDatabaseClient(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, JJAppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
    }

    public static synchronized JJDatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new JJDatabaseClient(context);
        }
        return instance;
    }

    public JJAppDatabase getAppDatabase() {
        return db;
    }
}
