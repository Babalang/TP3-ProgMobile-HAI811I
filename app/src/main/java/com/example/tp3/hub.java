package com.example.tp3;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class hub extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState)	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hub);
        Button exo1_button = findViewById(R.id.btnNewRegistration);
        exo1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hub.this, inscription.class);
                startActivity(intent);
            }
        });
        Button exo2_button = findViewById(R.id.btnLogin);
        exo2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hub.this, login.class);
                startActivity(intent);
            }
        });
    }
}
