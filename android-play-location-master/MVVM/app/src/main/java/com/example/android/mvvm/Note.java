package com.example.android.mvvm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_database")
public class Note {
    @PrimaryKey(autoGenerate = true)
    int id;

    String name;

    public Note(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    String comment;
}
