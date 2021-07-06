package com.example.notasdemo.dao;

import androidx.room.*;

import com.example.notasdemo.model.Note;
import com.example.notasdemo.model.UserWithNote;
import com.example.notasdemo.model.User;

import java.util.Collection;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    void insert(User user);

    @Insert
    void registerUser(User user);

    @Delete
    void delete(User user);

    @Delete
    void reset(List<User> userData);

    @Query("UPDATE table_user SET email =:uText WHERE Id =:uID")
    void update (int uText, String uID);

    @Query("SELECT * FROM table_user")
    List<User> getAll();

    @Query("SELECT * FROM table_user WHERE email =:correo")
    User getUser(String correo);

    @Query("SELECT * FROM table_user WHERE id =:idUser")
    public UserWithNote getAllNote(int idUser);
}

