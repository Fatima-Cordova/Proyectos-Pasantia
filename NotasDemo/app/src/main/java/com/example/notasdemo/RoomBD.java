package com.example.notasdemo;


import androidx.room.*;
import android.content.Context;
import android.service.autofill.UserData;

import com.example.notasdemo.dao.NotaDao;
import com.example.notasdemo.model.Nota;
import com.example.notasdemo.dao.UserDao;
import com.example.notasdemo.model.User;

@Database(entities = {Nota.class, User.class}, version = 1, exportSchema = false)
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
