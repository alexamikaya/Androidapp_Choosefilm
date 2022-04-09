package com.example.myfilm.recommend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myfilm.R;
import com.example.myfilm.Start;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecommendMusic extends AppCompatActivity {


    public class RecommendDetective extends AppCompatActivity {
        private androidx.recyclerview.widget.RecyclerView RecyclerView;
        private androidx.recyclerview.widget.RecyclerView.Adapter Adapter;
        private androidx.recyclerview.widget.RecyclerView.LayoutManager LayoutManager;
        Button mainmenu;
        private String clientID;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_recommend_detective);

            clientID = FirebaseAuth.getInstance().getCurrentUser().getUid();

            RecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            RecyclerView.setNestedScrollingEnabled(false);
            RecyclerView.setHasFixedSize(true);
            LayoutManager = new LinearLayoutManager(RecommendMusic.this);
            RecyclerView.setLayoutManager(LayoutManager);
            Adapter = new Adapters(getDataSet(), RecommendMusic.this);
            RecyclerView.setAdapter(Adapter);

            getClientId();
            mainmenu = findViewById(R.id.mainmenu);
            mainmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(RecommendMusic.this, Start.class));
                    finish();
                }
            });

        }

        private void getClientId() {

            DatabaseReference recommendDb = FirebaseDatabase.getInstance().getReference().child("Users").child(clientID).child("connect").child("recommend");
            recommendDb.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot recommend : snapshot.getChildren()) {
                            Recommendation(recommend.getKey());
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        private void Recommendation(String key) {
            DatabaseReference filmDb = FirebaseDatabase.getInstance().getReference().child("Films").child("Music").child(key);

            filmDb.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        String name = "";
                        String image = "";
                        String desc = "";
                        if (snapshot.child("name").getValue() != null) {
                            name = snapshot.child("name").getValue().toString();
                        }
                        if (snapshot.child("image").getValue() != null) {
                            image = snapshot.child("image").getValue().toString();
                        }
                        if (snapshot.child("desc").getValue() != null) {
                            desc = snapshot.child("desc").getValue().toString();
                        }

                        Objects obj = new Objects(name, image, desc);
                        results.add(obj);
                        Adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        private ArrayList<Objects> results = new ArrayList<Objects>();

        private List<Objects> getDataSet() {
            return results;
        }
    }
}