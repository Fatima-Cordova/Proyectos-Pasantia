package com.example.guardarspinner.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.guardarspinner.model.Usuario;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UsuarioDao {

    @Insert(onConflict = REPLACE)
    void insert(Usuario usuario);

    @Query("SELECT * FROM table_user order by id desc limit 1")
    Usuario getLastUserInformationSave();

    @Query("UPDATE table_user SET user =:sText WHERE id =:sID")
    void update (int sID, String sText);

    @Query("SELECT * FROM table_user")
    List<Usuario> getAll();

    @Query("SELECT * FROM table_user WHERE id =:idUser")
    Usuario getUser(int idUser);
}



