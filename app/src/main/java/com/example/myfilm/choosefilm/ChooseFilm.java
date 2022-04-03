package com.example.myfilm.choosefilm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myfilm.R;

public class ChooseFilm extends AppCompatActivity {
    RadioGroup radioGroup;
    Button choose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_film);
        radioGroup = findViewById(R.id.radioButton);
        choose = findViewById(R.id.choose);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = radioGroup.getCheckedRadioButtonId();
                RadioButton choose_genre = radioGroup.findViewById(checkedId);
                if (choose_genre == null){
                    Toast.makeText(ChooseFilm.this, "Пожалуйста, выберите жанр", Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(ChooseFilm.this, Comedy.class));
                break;
            case ("Детектив"):
                startActivity(new Intent(ChooseFilm.this, Detective.class));
                break;
            case ("Мультфильм"):
                startActivity(new Intent(ChooseFilm.this, Cartoon.class));
                break;
            case ("Фантастику"):
                startActivity(new Intent(ChooseFilm.this, Fantastic.class));
                break;
            case ("Мелодраму"):
                startActivity(new Intent(ChooseFilm.this, Romantic.class));
                break;
            case ("Боевик"):
                startActivity(new Intent(ChooseFilm.this, Fight.class));
                break;
            case ("Сказку"):
                startActivity(new Intent(ChooseFilm.this, Fairytale.class));
                break;
            case ("Мюзикл"):
                startActivity(new Intent(ChooseFilm.this, Music.class));
                break;
            default:
                break;
        }
    }
}
