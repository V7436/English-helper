package com.example.dictionary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText en;
    EditText rus1;
    EditText rus2;
    EditText rus3;
    EditText word_priority;
    Button updateButton, delete_button, plusButton, minusButton;

    String id, en_, rus1_, rus2_, rus3_, prior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        en = findViewById(R.id.enWord2);
        rus1 = findViewById(R.id.rusWord12);
        rus2 = findViewById(R.id.rusWord22);
        rus3 = findViewById(R.id.rusWord32);
        word_priority = findViewById(R.id.word_priority2);
        updateButton = findViewById(R.id.updateButton);
        delete_button= findViewById(R.id.deleteButton);
        plusButton = findViewById(R.id.plusButton);
        minusButton= findViewById(R.id.minusButton);
        prior = word_priority.getText().toString().trim();

        getAndSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateData(id, en.getText().toString().trim(),
                        rus1.getText().toString().trim(),
                        rus2.getText().toString().trim(),
                        rus3.getText().toString().trim(),
                        word_priority.getText().toString().trim());

            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(prior);
                number++;
                prior = Integer.toString(number);
                word_priority.setText(prior);
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateData(id, en.getText().toString().trim(),
                        rus1.getText().toString().trim(),
                        rus2.getText().toString().trim(),
                        rus3.getText().toString().trim(),
                        word_priority.getText().toString().trim());
            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(prior);
                number--;
                prior = Integer.toString(number);
                word_priority.setText(prior);
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateData(id, en.getText().toString().trim(),
                        rus1.getText().toString().trim(),
                        rus2.getText().toString().trim(),
                        rus3.getText().toString().trim(),
                        word_priority.getText().toString().trim());
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("en") &&
                getIntent().hasExtra("rus1") && getIntent().hasExtra("rus2") &&
                getIntent().hasExtra("rus3") && getIntent().hasExtra("prior")){
            id = getIntent().getStringExtra("id");
            en_ = getIntent().getStringExtra("en");
            rus1_ = getIntent().getStringExtra("rus1");
            rus2_ = getIntent().getStringExtra("rus2");
            rus3_ = getIntent().getStringExtra("rus3");
            prior = getIntent().getStringExtra("prior");

            en.setText(en_);
            rus1.setText(rus1_);
            rus2.setText(rus2_);
            rus3.setText(rus3_);
            word_priority.setText(prior);
        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}