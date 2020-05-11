package com.example.dbroomexample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rangliste")
public class Fahrer {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "team")
    private String team;

    @NonNull
    @ColumnInfo(name = "punkte")
    private int punkte;

    @NonNull
    @ColumnInfo(name = "siege")
    private int siege;

    public Fahrer(String name, String team, int punkte, int siege){
        this.name = name;
        this.team = team;
        this.punkte = punkte;
        this.siege = siege;
    }

    public String getName(){return this.name;}

    public String getTeam() {return this.team;}

    public int getPunkte() {return this.punkte;}

    public int getSiege() {return this.siege;}

    public int getId() {return this.id;}

    public void setId(int id) {this.id = id;}
}
