package com.example.android.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {


    public static final String EXTRA_NAME =
            "com.codinginflow.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_COMMENT =
            "com.codinginflow.architectureexample.EXTRA_DESCRIPTION";
    private EditText editTextTitle;
    private EditText editTextDescription;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_name);
        editTextDescription = findViewById(R.id.edit_text_comment);
        
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_cross);
        setTitle("Add Note");
    }

    private void saveNote() {
        String name = editTextTitle.getText().toString();
        String comment = editTextDescription.getText().toString();

        if (name.trim().isEmpty() || comment.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a name and comment", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_COMMENT, comment);
        
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
