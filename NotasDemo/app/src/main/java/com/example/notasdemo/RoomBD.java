package com.example.notasdemo;


import androidx.room.*;
import android.content.Context;

import com.example.notasdemo.dao.NotaDao;
import com.example.notasdemo.model.Note;
import com.example.notasdemo.dao.UserDao;
import com.example.notasdemo.model.User;

@Database(entities = {Note.class, User.class}, version = 1, exportSchema = false)
public abstract class RoomBD extends RoomDatabase {

    private static RoomBD baseDeDatos;
    private static String DATABASE_NAME = "baseDeDatos";

    public synchronized static RoomBD getInstance(Context context) {
        if (baseDeDatos == null) {
            baseDeDatos = Room.databaseBuilder(context.getApplicationContext(), RoomBD.class,
                    DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return baseDeDatos;
    }

    public abstract NotaDao notaDao();

    public abstract UserDao userDao();
}
