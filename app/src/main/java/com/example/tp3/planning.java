package com.example.tp3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class planning extends AppCompatActivity {
    private DataSet dataSet;
    private EditText slot1, slot2, slot3, slot4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planning_activity);
        dataSet = new DataSet(this, "inscription.db", null);
        slot1 = findViewById(R.id.slot1);
        slot2 = findViewById(R.id.slot2);
        slot3 = findViewById(R.id.slot3);
        slot4 = findViewById(R.id.slot4);

        Button btnValidate = findViewById(R.id.btnValidate);
        btnValidate.setOnClickListener(v -> {
            if (savePlanning()) {
                Intent intent = new Intent(planning.this, planningsummary.class);
                startActivity(intent);
            }
        });
    }
    private boolean savePlanning() {
        String slot1Str = slot1.getText().toString();
        String slot2Str = slot2.getText().toString();
        String slot3Str = slot3.getText().toString();
        String slot4Str = slot4.getText().toString();
        // Validate if all fields are filled
        if (slot1Str.isEmpty() || slot2Str.isEmpty() || slot3Str.isEmpty() || slot4Str.isEmpty()) {
            Toast.makeText(this, "Please fill in all slots", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Initialize db here

        long newRowId; // Initialize newRowId here
        try (SQLiteDatabase db = dataSet.getWritableDatabase()) {
            // Prepare the data to insert
            ContentValues values = new ContentValues();
            values.put(DataSet.COLUMN_TIME_SLOT, "08h-10h");
            values.put(DataSet.COLUMN_ACTIVITY, slot1Str);
            newRowId = db.insert(DataSet.TABLE_PLANNING, null, values);
            if (newRowId == -1) {
                Toast.makeText(this, "Failed to save slot 08h-10h", Toast.LENGTH_SHORT).show();
                return false;
            }
            values.put(DataSet.COLUMN_TIME_SLOT, "10h-12h");
            values.put(DataSet.COLUMN_ACTIVITY, slot2Str);
            newRowId = db.insert(DataSet.TABLE_PLANNING, null, values);
            if (newRowId == -1) {
                Toast.makeText(this, "Failed to save slot 10h-12h", Toast.LENGTH_SHORT).show();
                return false;
            }
            values.put(DataSet.COLUMN_TIME_SLOT, "14h-16h");
            values.put(DataSet.COLUMN_ACTIVITY, slot3Str);
            newRowId = db.insert(DataSet.TABLE_PLANNING, null, values);
            if (newRowId == -1) {
                Toast.makeText(this, "Failed to save slot 14h-16h", Toast.LENGTH_SHORT).show();
                return false;
            }
            values.put(DataSet.COLUMN_TIME_SLOT, "16h-18h");
            values.put(DataSet.COLUMN_ACTIVITY, slot4Str);
            newRowId = db.insert(DataSet.TABLE_PLANNING, null, values);
            if (newRowId == -1) {
                Toast.makeText(this, "Failed to save slot 16h-18h", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        Toast.makeText(this, "Planning saved successfully", Toast.LENGTH_SHORT).show();
        return true;
    }
}