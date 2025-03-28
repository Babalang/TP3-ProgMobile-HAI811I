package com.example.tp3;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class hub extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState)	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hub);
        Button exo1_button = findViewById(R.id.btnNewRegistration);
        exo1_button.setOnClickListener(v -> {
            Intent intent = new Intent(hub.this, inscription.class);
            startActivity(intent);
        });
        Button exo2_button = findViewById(R.id.btnLogin);
        exo2_button.setOnClickListener(v -> {
            Intent intent = new Intent(hub.this, login.class);
            startActivity(intent);
        });
    }
}
