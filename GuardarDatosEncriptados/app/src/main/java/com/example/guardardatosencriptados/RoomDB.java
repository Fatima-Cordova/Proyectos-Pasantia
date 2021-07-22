package com.example.guardardatosencriptados;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.guardardatosencriptados.dao.UsuarioDao;
import com.example.guardardatosencriptados.model.Usuario;

@Database(entities = {Usuario.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB baseDeDatos;
    private static String DATABASE_NAME = "baseDeDatos";

    public synchronized static RoomDB getInstance(Context context) {
        if (baseDeDatos == null) {
            baseDeDatos = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class,
                    DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return baseDeDatos;
    }
    public abstract UsuarioDao usuariosDao();
}
