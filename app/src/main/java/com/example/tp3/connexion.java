package com.example.tp3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class connexion extends AppCompatActivity {
    private DataSet DataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion_activity);

        DataBase = new DataSet(this, "inscription.db", null);

        Button btnLogin = findViewById(R.id.btnLogin);
        EditText editLogin = findViewById(R.id.editLogin);
        EditText editPassword = findViewById(R.id.editPassword);

        btnLogin.setOnClickListener(v -> {
            String login = editLogin.getText().toString();
            String password = editPassword.getText().toString();

            if (checkUser(login, password)) {
                Intent intent = new Intent(connexion.this, planning.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(connexion.this, R.string.invalid, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkUser(String login, String password) {
        try (SQLiteDatabase db = DataBase.getReadableDatabase(); Cursor cursor = db.query(DataSet.TABLE_USER_DATA,
                null,
                DataSet.COLUMN_LOGIN + " = ? AND " + DataSet.COLUMN_PASSWORD + " = ?",
                new String[]{login, password},
                null,
                null,
                null)) {

            return cursor.moveToFirst();

        }
    }
}