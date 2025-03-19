package com.example.tp3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class login extends Activity {
    private DataSet DataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        DataBase = new DataSet(this, "inscription.db", null, 1);

        Button btnLogin = findViewById(R.id.btnLogin);
        EditText editLogin = findViewById(R.id.editLogin);
        EditText editPassword = findViewById(R.id.editPassword);

        btnLogin.setOnClickListener(v -> {
            String login = editLogin.getText().toString();
            String password = editPassword.getText().toString();

            if (checkUser(login, password)) {
                Intent intent = new Intent(login.this, planning.class);
                startActivity(intent);
                finish(); // Close the LoginActivity
            } else {
                Toast.makeText(login.this, "Invalid login or password", Toast.LENGTH_SHORT).show();
                // The user can try again
            }
        });
    }

    private boolean checkUser(String login, String password) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = DataBase.getReadableDatabase();
            cursor = db.query(DataSet.TABLE_USER_DATA, // Table
                    null, // All columns
                    DataSet.COLUMN_LOGIN + " = ? AND " + DataSet.COLUMN_PASSWORD + " = ?", // Where clause
                    new String[]{login, password}, // Where args
                    null, // Group by
                    null, // Having
                    null); // Order by

            return cursor.moveToFirst(); // Returns true if a row is found (user exists)

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }
}