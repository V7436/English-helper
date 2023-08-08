package com.example.dictionary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    Activity activity;
    int choice;

    ArrayList word_id_array, en_word_array, rus_word1_array, rus_word2_array, rus_word3_array, word_pr_array;

    CustomAdapter(Activity activity, Context context, ArrayList word_id_array,
                  ArrayList en_word,
                  ArrayList rus_word1,
                  ArrayList rus_word2,
                  ArrayList rus_word3,
                  ArrayList word_pr,
                  int choice){
        this.activity = activity;
        this.context = context;
        this.word_id_array = word_id_array;
        this.en_word_array = en_word;
        this.rus_word1_array = rus_word1;
        this.rus_word2_array = rus_word2;
        this.rus_word3_array = rus_word3;
        this.word_pr_array = word_pr;
        this.choice = choice;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        if(choice == 1){
            holder.word_id.setText(String.valueOf(word_id_array.get(position)));
            holder.en_word.setText(String.valueOf(en_word_array.get(position)));
            holder.rus_word1.setText(String.valueOf(rus_word1_array.get(position)));
            holder.rus_word2.setText(String.valueOf(rus_word2_array.get(position)));
            holder.rus_word3.setText(String.valueOf(rus_word3_array.get(position)));
            holder.word_pr.setText(String.valueOf(word_pr_array.get(position)));
        }
        if(choice == 2){
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList = MySort(word_pr_array);

            holder.word_id.setText(String.valueOf(word_id_array.get(arrayList.get(position))));
            holder.en_word.setText(String.valueOf(en_word_array.get(arrayList.get(position))));
            holder.rus_word1.setText(String.valueOf(rus_word1_array.get(arrayList.get(position))));
            holder.rus_word2.setText(String.valueOf(rus_word2_array.get(arrayList.get(position))));
            holder.rus_word3.setText(String.valueOf(rus_word3_array.get(arrayList.get(position))));
            holder.word_pr.setText(String.valueOf(word_pr_array.get(arrayList.get(position))));
        }

        holder.word_pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                if(choice == 1){
                    intent.putExtra("id", String.valueOf(word_id_array.get(holder.getAdapterPosition())));
                    intent.putExtra("en", String.valueOf(en_word_array.get(holder.getAdapterPosition())));
                    intent.putExtra("rus1", String.valueOf(rus_word1_array.get(holder.getAdapterPosition())));
                    intent.putExtra("rus2", String.valueOf(rus_word2_array.get(holder.getAdapterPosition())));
                    intent.putExtra("rus3", String.valueOf(rus_word3_array.get(holder.getAdapterPosition())));
                    intent.putExtra("prior", String.valueOf(word_pr_array.get(holder.getAdapterPosition())));

                    activity.startActivityForResult(intent, 1);
                }
                if(choice == 2){
                    ArrayList<Integer> arrayList;
                    arrayList = MySort(word_pr_array);

                    intent.putExtra("id", String.valueOf(word_id_array.get(arrayList.get(position))));
                    intent.putExtra("en", String.valueOf(en_word_array.get(arrayList.get(position))));
                    intent.putExtra("rus1", String.valueOf(rus_word1_array.get(arrayList.get(position))));
                    intent.putExtra("rus2", String.valueOf(rus_word2_array.get(arrayList.get(position))));
                    intent.putExtra("rus3", String.valueOf(rus_word3_array.get(arrayList.get(position))));
                    intent.putExtra("prior", String.valueOf(word_pr_array.get(arrayList.get(position))));

                    activity.startActivityForResult(intent, 1);
                }
            }
        });

        holder.word_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                if(choice == 1){
                    intent.putExtra("id", String.valueOf(word_id_array.get(holder.getAdapterPosition())));
                    intent.putExtra("en", String.valueOf(en_word_array.get(holder.getAdapterPosition())));
                    intent.putExtra("rus1", String.valueOf(rus_word1_array.get(holder.getAdapterPosition())));
                    intent.putExtra("rus2", String.valueOf(rus_word2_array.get(holder.getAdapterPosition())));
                    intent.putExtra("rus3", String.valueOf(rus_word3_array.get(holder.getAdapterPosition())));
                    intent.putExtra("prior", String.valueOf(word_pr_array.get(holder.getAdapterPosition())));

                    activity.startActivityForResult(intent, 1);
                }
                if(choice == 2){
                    ArrayList<Integer> arrayList;
                    arrayList = MySort(word_pr_array);

                    intent.putExtra("id", String.valueOf(word_id_array.get(arrayList.get(position))));
                    intent.putExtra("en", String.valueOf(en_word_array.get(arrayList.get(position))));
                    intent.putExtra("rus1", String.valueOf(rus_word1_array.get(arrayList.get(position))));
                    intent.putExtra("rus2", String.valueOf(rus_word2_array.get(arrayList.get(position))));
                    intent.putExtra("rus3", String.valueOf(rus_word3_array.get(arrayList.get(position))));
                    intent.putExtra("prior", String.valueOf(word_pr_array.get(arrayList.get(position))));

                    activity.startActivityForResult(intent, 1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return word_id_array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView word_id, en_word, rus_word1, rus_word2, rus_word3, word_pr;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            word_id = itemView.findViewById(R.id.word_id);
            en_word = itemView.findViewById(R.id.en_word);
            rus_word1 = itemView.findViewById(R.id.rus_word1);
            rus_word2 = itemView.findViewById(R.id.rus_word2);
            rus_word3 = itemView.findViewById(R.id.rus_word3);
            word_pr = itemView.findViewById(R.id.word_pr);
        }
    }
    public ArrayList<Integer> MySort(ArrayList word_pr){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int j = 1; j < word_pr.size(); j++){
            for(int i = 0; i < word_pr.size(); i++){
                String x = String.valueOf(word_pr.get(i));
                int a = Integer.parseInt (x);
                if(a == j){
                    arrayList.add(i);
                }
            }
        }
        return arrayList;
    }
}
