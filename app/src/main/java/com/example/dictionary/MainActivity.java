package com.example.dictionary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dictionary.MyDatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button, test_button;

    MyDatabaseHelper myDB;
    ArrayList<String> _id, EN, RUS1, RUS2, RUS3, priority;
    CustomAdapter customAdapter;
    int choice = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        test_button = findViewById(R.id.test_button);
        test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, chooseTest.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        _id = new ArrayList<>();
        EN = new ArrayList<>();
        RUS1 = new ArrayList<>();
        RUS2 = new ArrayList<>();
        RUS3= new ArrayList<>();
        priority  = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, this, _id, EN, RUS1, RUS2, RUS3, priority, choice);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                _id.add(cursor.getString(0));
                EN.add(cursor.getString(1));
                RUS1.add(cursor.getString(2));
                RUS2.add(cursor.getString(3));
                RUS3.add(cursor.getString(4));
                priority.add(cursor.getString(5));
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.sort){
            choice = 2;
            customAdapter = new CustomAdapter(MainActivity.this, this, _id, EN, RUS1, RUS2, RUS3, priority, choice);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
        return super.onOptionsItemSelected(item);
    }
}