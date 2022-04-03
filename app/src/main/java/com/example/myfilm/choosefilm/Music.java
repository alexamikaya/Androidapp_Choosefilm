package com.example.myfilm.choosefilm;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfilm.R;
import com.example.myfilm.Start;
import com.example.myfilm.arrayAdapter;
import com.example.myfilm.cards;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class Music extends AppCompatActivity {
    private cards cards_data[];
    private ArrayAdapter arrayAdapter;
    private FirebaseAuth Auth;
    Button mainmenu;
    private String clientUId;

    List<cards> Items;
    private DatabaseReference usersdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detective);
        usersdb = FirebaseDatabase.getInstance().getReference().child("Users");
        Auth = FirebaseAuth.getInstance();
        clientUId = Auth.getCurrentUser().getUid();

        Items = new ArrayList<cards>();
        getfilmJanre();
        arrayAdapter = new arrayAdapter(this, R.layout.cardview, Items);
        DatabaseReference Filmdb = FirebaseDatabase.getInstance().getReference().child("Films").child("Music");
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!");
                Items.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                cards obj = (cards) dataObject;
                String Id = obj.getId();
                usersdb.child(clientUId).child("connect").child("dislike").child(Id).setValue(true);
                Filmdb.child(Id).child("connect").child("dislike").child(clientUId).setValue(true);
                Toast.makeText(Music.this, "Left", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onRightCardExit(Object dataObject) {
                cards obj = (cards) dataObject;
                String Id = obj.getId();
                usersdb.child(clientUId).child("connect").child("like").child(Id).setValue(true);
                Filmdb.child(Id).child("connect").child("like").child(clientUId).setValue(true);
                DatabaseReference ConnectDb = usersdb.child(clientUId).child("connect").child("like");
                ConnectDb.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot d : snapshot.getChildren()) {
                                Filmdb.child(Id).child("connect").child("recommendfilm").child(d.getKey()).setValue(true);
                            }
                        }}
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                isConnect(Id);

                Toast.makeText(Music.this, "Right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                cards obj = (cards) dataObject;
                String Id = obj.getId();
                usersdb.child(clientUId).child("connect").child("dontwatch").child(Id).setValue(true);
                Filmdb.child(Id).child("connect").child("dontwatch").child(clientUId).setValue(true);

                Toast.makeText(Music.this, "Item Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Music.this, Music.class));

            }
        });




        mainmenu = findViewById(R.id.mainmenu);
        mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Music.this, Start.class));
                finish();
            }
        });
    }

    private void isConnect(String filmId) {
        DatabaseReference Filmdb = FirebaseDatabase.getInstance().getReference().child("Films").child("Detective");
        DatabaseReference filmsDb = Filmdb.child(filmId).child("connect").child("recommendfilm");
        filmsDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {

                        Toast.makeText(Music.this, "new Connect", Toast.LENGTH_LONG).show();


                        usersdb.child(d.getKey()).child("connect").child("recommend").child(clientUId).setValue(true);
                        usersdb.child(clientUId).child("connect").child("recommend").child(d.getKey()).setValue(true);

                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }







    public void getfilmJanre() {

        usersdb = FirebaseDatabase.getInstance().getReference().child("Users");
        DatabaseReference Filmdb = FirebaseDatabase.getInstance().getReference().child("Films").child("Music");
        Filmdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists() && !snapshot.child("connect").child("dislike").hasChild(clientUId) && !snapshot.child("connect").child("like").hasChild(clientUId) && !snapshot.child("connect").child("dontwatch").hasChild(clientUId)) {

                    cards item = new cards(snapshot.getKey(), snapshot.child("name").getValue().toString(), snapshot.child("image").getValue().toString());
                    Items.add(item);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
