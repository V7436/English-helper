package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.dictionary.MyDatabaseHelper;

public class AddActivity extends AppCompatActivity {
    EditText en;
    EditText rus1;
    EditText rus2;
    EditText rus3;
    EditText word_priority;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        en = findViewById(R.id.enWord);
        rus1 = findViewById(R.id.rusWord1);
        rus2 = findViewById(R.id.rusWord2);
        rus3 = findViewById(R.id.rusWord3);
        word_priority = findViewById(R.id.word_priority);
        add_button = findViewById(R.id.button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper db = new MyDatabaseHelper(AddActivity.this);
                db.addWord(en.getText().toString().trim(),
                        rus1.getText().toString().trim(),
                        rus2.getText().toString().trim(),
                        rus3.getText().toString().trim(),
                        word_priority.getText().toString().trim());
            }
        });


    }
}