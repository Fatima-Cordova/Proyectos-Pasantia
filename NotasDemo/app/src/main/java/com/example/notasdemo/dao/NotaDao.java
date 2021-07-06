package com.example.notasdemo.dao;

import androidx.room.*;

import com.example.notasdemo.model.Note;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface NotaDao {
    @Insert(onConflict = REPLACE)
    void insert(Note nota);

    @Delete
    void delete(Note nota);

    @Delete
    void reset(List<Note> mainData);

    @Query("UPDATE table_name SET text =:sText WHERE ID =:sID")
    void update (int sID, String sText);

    @Query("SELECT * FROM table_name")
    List<Note> getAll();
}