package com.example.guardarspinner.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_user")
public class Usuario implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "idSocialIcon")
    public String idSocialIcon;

    @ColumnInfo(name = "user")
    public String user;


    @ColumnInfo(name = "nameNetworkSocial")
    public String nameNetworkSocial;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdSocialIcon() {
        return idSocialIcon;
    }

    public void setIdSocialIcon(String idSocialIcon) {
        this.idSocialIcon = idSocialIcon;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNameNetworkSocial() {
        return nameNetworkSocial;
    }

    public void setNameNetworkSocial(String nameNetworkSocial) {
        this.nameNetworkSocial = nameNetworkSocial;
    }
}
