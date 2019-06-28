package com.example.android.mvvm;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = Note.class,version = 1)
public abstract class Notedb extends RoomDatabase {

    private static Notedb instance;

    public abstract NoteDao noteDao();

    public static synchronized Notedb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), Notedb.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(rcall)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback rcall= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Populatedb(instance).execute();
        }
    };

    private static class Populatedb extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        public Populatedb(Notedb db) {
            super();
            noteDao=db.noteDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Aditya","HI"));
            noteDao.insert(new Note("Aditya1","HI 1"));
            noteDao.insert(new Note("Aditya2","HI 2"));

            return null;
        }
    }
}
