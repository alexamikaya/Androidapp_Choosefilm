package com.example.myfilm.recommend;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfilm.R;
import com.example.myfilm.Start;
import com.example.myfilm.choosefilm.Cartoon;
import com.example.myfilm.choosefilm.Comedy;
import com.example.myfilm.choosefilm.Fairytale;
import com.example.myfilm.choosefilm.Fantastic;
import com.example.myfilm.choosefilm.Fight;
import com.example.myfilm.choosefilm.Music;
import com.example.myfilm.choosefilm.Romantic;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Recommend extends AppCompatActivity {
    RadioGroup radioGroup;
    Button choose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        radioGroup = findViewById(R.id.radioButton);
        choose = findViewById(R.id.choose);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = radioGroup.getCheckedRadioButtonId();
                RadioButton choose_genre = radioGroup.findViewById(checkedId);
                if (choose_genre == null){
                    Toast.makeText(Recommend.this, "Пожалуйста, выберите жанр", Toast.LENGTH_SHORT).show();
                }
                else {
                    final String genre = choose_genre.getText().toString();
                    start(genre);
                }
            }
        });

    }
    protected void start(String genre) {
        switch (genre) {
            case ("Комедию"):
                startActivity(new Intent(Recommend.this, RecommendComedy.class));
                break;
            case ("Детектив"):
                startActivity(new Intent(Recommend.this, RecommendDetective.class));
                break;
            case ("Мультфильм"):
                startActivity(new Intent(Recommend.this, RecommendCartoon.class));
                break;
            case ("Фантастику"):
                startActivity(new Intent(Recommend.this, RecommendFantastic.class));
                break;
            case ("Мелодраму"):
                startActivity(new Intent(Recommend.this, RecommendRomantic.class));
                break;
            case ("Боевик"):
                startActivity(new Intent(Recommend.this, RecommendFight.class));
                break;
            case ("Сказку"):
                startActivity(new Intent(Recommend.this, RecommendFairytale.class));
                break;
            case ("Мюзикл"):
                startActivity(new Intent(Recommend.this, RecommendMusic.class));
                break;
            default:
                break;
        }
    }
}