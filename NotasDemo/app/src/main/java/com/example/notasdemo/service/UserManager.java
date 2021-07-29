package com.example.notasdemo.service;

import android.content.Context;

import com.example.notasdemo.RoomBD;
import com.example.notasdemo.model.User;
import com.example.notasdemo.util.Cypher;

public class UserManager {

    private RoomBD baseDeDatos;


    public UserManager(Context context) {
        baseDeDatos = RoomBD.getInstance(context);
    }

    public boolean registrarNuevoUsuario(String email, String pass){
        User user = new User();
        boolean nuevoUsuario;
        try {
            user.setEmail(Cypher.encrypt(email));
            user.setPass(Cypher.encrypt(pass));
            baseDeDatos.userDao().insert(user);
            nuevoUsuario = true;
        } catch (Exception exception) {
            nuevoUsuario = false;
        }
        return nuevoUsuario;
    }

    public User validar(String correo) {
        try {
            User user = baseDeDatos.userDao().getUser(Cypher.encrypt(correo));
            if (user != null) {
                user.setPass(Cypher.decrypt(user.getPass()));
                return user;
            } else {
                return simpleUser();
            }
        } catch (Exception exception) {
            return simpleUser();
        }
    }

    private User simpleUser() {
        User user = new User();
        user.setId(0);
        return user;
    }
}
