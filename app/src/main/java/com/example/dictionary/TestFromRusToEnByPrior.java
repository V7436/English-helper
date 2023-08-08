package com.example.dictionary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TestFromRusToEnByPrior extends AppCompatActivity {

    TextView question, answer, word_id;
    EditText prior;
    MyDatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Button answer_button, next_button;
    Cursor userCursor;
    ArrayList<Integer> array = new ArrayList<>();
    int i = 0;
    int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_from_rus_to_en_by_prior);

        question = findViewById(R.id.rus_question2);
        answer = findViewById(R.id.en_answer2);
        prior = findViewById(R.id.rus_to_en_prior2);
        word_id = findViewById(R.id.rus_word_id2);

        databaseHelper = new MyDatabaseHelper(getApplicationContext());
        db = databaseHelper.getReadableDatabase();

        id = (int) ( Math.random() * 6 );

        Cursor cursor = db.rawQuery("select * from "+ MyDatabaseHelper.TABLE_NAME, null);
        int count = cursor.getCount();
        cursor.close();
        next_button = findViewById(R.id.rus_next_button);

        ArrayList<String> rusWords = new ArrayList<>();
        ArrayList<String> rusWords2 = new ArrayList<>();
        ArrayList<String> rusWords3 = new ArrayList<>();
        ArrayList<String> enWords = new ArrayList<>();
        ArrayList<String> wordsId = new ArrayList<>();
        ArrayList<String> word_pr_array = new ArrayList<>();

        word_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestFromRusToEnByPrior.this, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(wordsId.get(id)));
                intent.putExtra("en", String.valueOf(enWords.get(id)));
                intent.putExtra("rus1", String.valueOf(rusWords.get(id)));
                intent.putExtra("rus2", String.valueOf(rusWords2.get(id)));
                intent.putExtra("rus3", String.valueOf(rusWords3.get(id)));
                intent.putExtra("prior", String.valueOf(word_pr_array.get(id)));

                startActivity(intent);
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText("");
                word_id.setText("");
                String pr = prior.getText().toString().trim();

                if(i == 0) {
                    String query = String.format("SELECT * FROM %s WHERE %s=?", MyDatabaseHelper.TABLE_NAME, MyDatabaseHelper.KEY_PRIORITY);
                    Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(pr)});
                    while (cursor.moveToNext()) {
                        wordsId.add(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.KEY_ID)));
                        rusWords.add(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.KEY_RUS1)));
                        rusWords2.add(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.KEY_RUS2)));
                        rusWords3.add(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.KEY_RUS3)));
                        enWords.add(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.KEY_EN)));
                        word_pr_array.add(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.KEY_PRIORITY)));
                    }
                    i++;
                }
                id = Random(rusWords.size());
                question.setText(rusWords.get(id));
                answer_button = findViewById(R.id.en_answer_button);
                answer_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answer.setText(enWords.get(id));
                        word_id.setText(wordsId.get(id));
                    }
                });
                cursor.close();
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
                    Toast toast = Toast.makeText(this, "Array has been reset",Toast.LENGTH_SHORT);
                    toast.show();
                }
                return number;
            }else{
                x = true;
            }
        }
        return count;
    }
}