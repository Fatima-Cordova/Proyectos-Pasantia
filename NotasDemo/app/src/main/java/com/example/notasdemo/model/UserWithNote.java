package com.example.notasdemo.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithNote {

    @Embedded
    public User user;

    @Relation(parentColumn = "id", entityColumn = "idUser", entity = Note.class)
    public List<Note> notas;
}
