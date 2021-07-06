package com.example.notasdemo.model;

import androidx.room.*;

import java.io.Serializable;

@Entity(tableName = "table_name")
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    private int idUser;

    @ColumnInfo(name = "text")
    private String text;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
