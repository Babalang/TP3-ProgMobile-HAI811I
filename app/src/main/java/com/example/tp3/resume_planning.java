package com.example.tp3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class resume_planning extends AppCompatActivity {
    private DataSet dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_planning_activity);
        dataSet = new DataSet(this, "inscription.db", null);

        TextView summaryText = findViewById(R.id.summaryText);
        String summary = getPlanningSummary();
        summaryText.setText(summary);
    }

    private String getPlanningSummary() {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        StringBuilder summary = new StringBuilder();
        try {
            db = dataSet.getReadableDatabase();
            cursor = db.query(DataSet.TABLE_PLANNING, null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    String timeSlot = cursor.getString(cursor.getColumnIndexOrThrow(DataSet.COLUMN_TIME_SLOT));
                    String activity = cursor.getString(cursor.getColumnIndexOrThrow(DataSet.COLUMN_ACTIVITY));
                    summary.append(timeSlot).append(" : ").append(activity).append("\n");
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return summary.toString();
    }
}