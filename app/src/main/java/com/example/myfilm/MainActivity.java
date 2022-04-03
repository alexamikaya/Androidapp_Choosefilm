package com.example.myfilm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;


public class MainActivity extends AppCompatActivity {
    MaterialEditText email,password;
    Button register,login;

    FirebaseAuth mAuth;

    ProgressBar progressBar;
    String Email,Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Registration.class));
                finish();
            }
        });


    }


    private void login(){
        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();
        if (Email.isEmpty()){
            email.setError("Заполните поле!");
            email.requestFocus();
            return;
        }

        if(Password.isEmpty()){
            password.setError("Заполните поле - пароль");
            password.requestFocus();
            return;
        }
        if (Password.length()<6){
            password.setError("Пароль должен быть не менее 6 символов");
            password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this,Start.class));
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,"Ошибка", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}