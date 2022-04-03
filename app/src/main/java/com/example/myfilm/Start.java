package com.example.myfilm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfilm.choosefilm.ChooseFilm;
import com.example.myfilm.recommend.Recommend;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Start extends AppCompatActivity {

    Button logout, recommend, choose, rule;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        rule = findViewById(R.id.rule);
        recommend = findViewById(R.id.recommend);
        choose = findViewById(R.id.choose);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();
        final TextView welcome = (TextView) findViewById(R.id.welcome);
        final TextView Emailuser = (TextView) findViewById(R.id.user_email);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userlk = snapshot.getValue(User.class);
                if(userlk != null){
                    String Name = userlk.Name;
                    String Email = userlk.Email;

                    welcome.setText("Добро пожаловать, "+Name+"!");
                    Emailuser.setText("Ваш email - " + Email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Start.this, "Упс... ошибочка вышла =(", Toast.LENGTH_LONG).show();
            }
        });


        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Start.this, MainActivity.class));
            }
        });
        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(Start.this,Rules.class));
                finish();
            }
        });
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(Start.this, ChooseFilm.class));
                finish();
            }
        });

        recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(Start.this, Recommend.class));
                finish();
            }
        });
    }
}