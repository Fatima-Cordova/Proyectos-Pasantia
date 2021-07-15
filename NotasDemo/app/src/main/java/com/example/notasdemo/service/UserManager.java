package com.example.notasdemo.service;

import android.content.Context;

import com.example.notasdemo.RoomBD;
import com.example.notasdemo.model.User;

public class UserManager {

    private RoomBD baseDeDatos;


    public UserManager(Context context) {
        baseDeDatos = RoomBD.getInstance(context);
    }

    public void ingresar(String email, String pass){
        User user = new User();
        user.setEmail(email);
        user.setPass(pass);
        baseDeDatos.userDao().insert(user);
    }

    public User validar(String correo, String pass) {
        User user = baseDeDatos.userDao().getUser(correo);
        if (user != null) {
            return user;
        } else {
            user = new User();
            user.setId(0);
            return user;
        }
    }
}