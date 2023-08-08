package com.example.dictionary;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TestFromRusToEn extends AppCompatActivity {

    TextView question, answer, prior, word_id, wordsLeftCounter;
    MyDatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Button answer_button, next_button;
    Cursor userCursor;
    ArrayList<Integer> array = new ArrayList<>();
    int id = 1, wordsLeftCount = 0, wordsWas;;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_from_rus_to_en);

        question = findViewById(R.id.rus_question);
        answer = findViewById(R.id.en_answer);
        wordsLeftCounter = findViewById(R.id.wordsCounter2);
        prior = findViewById(R.id.rus_to_en_prior);
        word_id = findViewById(R.id.rus_word_id);
        databaseHelper = new MyDatabaseHelper(getApplicationContext());
        db = databaseHelper.getReadableDatabase();


        Cursor cursor = db.rawQuery("select * from "+ MyDatabaseHelper.TABLE_NAME, null);
        int count = cursor.getCount();
        cursor.close();

        ArrayList<String> rusWords = new ArrayList<>();
        ArrayList<String> rusWords2 = new ArrayList<>();
        ArrayList<String> rusWords3 = new ArrayList<>();
        ArrayList<String> enWords = new ArrayList<>();
        ArrayList<String> wordsId = new ArrayList<>();
        ArrayList<String> wordPr = new ArrayList<>();

        word_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestFromRusToEn.this, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(wordsId.get(id)));
                intent.putExtra("en", String.valueOf(enWords.get(id)));
                intent.putExtra("rus1", String.valueOf(rusWords.get(id)));
                intent.putExtra("rus2", String.valueOf(rusWords2.get(id)));
                intent.putExtra("rus3", String.valueOf(rusWords3.get(id)));
                intent.putExtra("prior", String.valueOf(wordPr.get(id)));

                startActivity(intent);
            }
        });

        Cursor cursor2 = db.rawQuery("SELECT * FROM " + MyDatabaseHelper.TABLE_NAME, null);
        while (cursor2.moveToNext()){
            wordsId.add(cursor2.getString(cursor2.getColumnIndex(MyDatabaseHelper.KEY_ID)));
            rusWords.add(cursor2.getString(cursor2.getColumnIndex(MyDatabaseHelper.KEY_RUS1)));
            rusWords2.add(cursor2.getString(cursor2.getColumnIndex(MyDatabaseHelper.KEY_RUS2)));
            rusWords3.add(cursor2.getString(cursor2.getColumnIndex(MyDatabaseHelper.KEY_RUS3)));
            enWords.add(cursor2.getString(cursor2.getColumnIndex(MyDatabaseHelper.KEY_EN)));
            wordPr.add(cursor2.getString(cursor2.getColumnIndex(MyDatabaseHelper.KEY_PRIORITY)));
        }
        wordsLeftCount = enWords.size();
        wordsWas = enWords.size();

        next_button = findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText("");
                word_id.setText("");
                prior.setText("");
                wordsLeftCounter.setText("");

                id = Random(rusWords.size());
                question.setText(rusWords.get(id));

                answer_button = findViewById(R.id.answer_button);
                answer_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answer.setText(enWords.get(id));
                        word_id.setText(wordsId.get(id));
                        prior.setText(wordPr.get(id));
                        wordsLeftCounter.setText(String.valueOf(wordsLeftCount));
                    }
                });
                wordsLeftCount--;
                cursor2.close();
            }
        });

    }
    /*
    @Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
        userCursor.close();
    }
     */
    public int Random(int count){
        boolean x = true;
        while (x){
            int number = (int) ( Math.random() * count );
            for (int i = 0; i < array.size(); i++){
                if(number == array.get(i)){
                    x = false;
                }
            }
            if(x){
                array.add(number);
                if(array.size()==count){
                    array.clear();
                    wordsLeftCount = wordsWas + 1;
                }
                return number;
            }else{
                x = true;
            }
        }
        return count;
    }
}


