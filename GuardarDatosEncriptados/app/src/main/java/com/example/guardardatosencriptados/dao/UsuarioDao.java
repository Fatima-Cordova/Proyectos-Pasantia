package com.example.guardardatosencriptados.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.guardardatosencriptados.model.Usuario;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UsuarioDao {

    @Insert(onConflict = REPLACE)
    void insert(Usuario usuario);

    @Delete
    void delete(Usuario usuario);

    @Delete
    void reset(List<Usuario> mainData);

    @Query("UPDATE table_notes SET nombre =:sText WHERE ID =:sID")
    void update (int sID, String sText);

    @Query("SELECT * FROM table_notes")
    List<Usuario> getAll();
}
