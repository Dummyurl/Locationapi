package com.example.android.mvvm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class Repository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allnotes;

    public Repository(Application application){
        Notedb dateabase = Notedb.getInstance(application);
        noteDao = dateabase.noteDao();
        allnotes=noteDao.getAllNotes();
    }

    public void insert(Note note){
        new InsertAsynTask(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateAsynTask(noteDao).execute(note);

    }

    public void delete(Note note){
        new DeleteAsynTask(noteDao).execute(note);

    }

    public LiveData<List<Note>> getAllNotes(){
        return allnotes;
    }

    public void deleteAllNotes(){
        new deleteAllNotesAsynTask(noteDao).execute();

    }

    private static class deleteAllNotesAsynTask extends AsyncTask<Void,Void,Void>{

        private NoteDao noteDao;

        public deleteAllNotesAsynTask(NoteDao noteDao) {
            super();
            this.noteDao=noteDao;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

    private static class DeleteAsynTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        public DeleteAsynTask(NoteDao noteDao) {
            super();
            this.noteDao=noteDao;

        }

        @Override
        protected Void doInBackground(Note... note) {
            noteDao.delete(note[0]);
            return null;
        }
    }


    private static class UpdateAsynTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        public UpdateAsynTask(NoteDao noteDao) {
            super();
            this.noteDao=noteDao;

        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }


    private static class InsertAsynTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        public InsertAsynTask(NoteDao noteDao) {
            super();
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
}
