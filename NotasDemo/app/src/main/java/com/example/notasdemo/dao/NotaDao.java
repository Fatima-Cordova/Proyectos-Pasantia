package com.example.notasdemo.dao;

import androidx.room.*;

import com.example.notasdemo.model.Nota;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface NotaDao {
    @Insert(onConflict = REPLACE)
    void insert(Nota nota);

    @Delete
    void delete(Nota nota);

    @Delete
    void reset(List<Nota> mainData);

    @Query("UPDATE table_name SET text =:sText WHERE ID =:sID")
    void update (int sID, String sText);

    @Query("SELECT * FROM table_name")
    List<Nota> getAll();
}