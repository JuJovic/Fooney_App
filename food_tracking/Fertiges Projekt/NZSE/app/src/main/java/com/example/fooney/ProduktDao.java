package com.example.fooney;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProduktDao {

    @Query("SELECT * FROM produkt")
    List<Produkt> getAll();

    @Query("SELECT * FROM Produkt WHERE pid IN (:userIds)")
    List<Produkt> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Produkt WHERE m_name LIKE :first " +
            " LIKE :last LIMIT 1")
    Produkt findByName(String first, String last);

    @Insert
    void insert(Produkt produkt);

    @Delete
    void delete(Produkt produkt);

    @Update
    void update(Produkt produkt);
}
