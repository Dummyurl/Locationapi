package com.example.android.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.example.android.mvvm.EXTRA_ID";
    public static final String EXTRA_NAME =
            "com.example.android.mvvm.EXTRA_NAME";
    public static final String EXTRA_COMMENT =
            "com.example.android.mvvm.EXTRA_COMMENT";
    private EditText editTextName;
    private EditText editTextComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextName = findViewById(R.id.edit_text_name);
        editTextComment = findViewById(R.id.edit_text_comment);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_cross);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            editTextComment.setText(intent.getStringExtra(EXTRA_COMMENT));
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String name = editTextName.getText().toString();
        String description = editTextComment.getText().toString();

        if (name.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a name and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_COMMENT, description);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
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

