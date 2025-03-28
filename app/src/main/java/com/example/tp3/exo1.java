package com.example.tp3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class exo1 extends Fragment {
    private View view;
    private DataSet DataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DataBase = new DataSet(getContext(), "inscription.db", null);
        view = inflater.inflate(R.layout.exo1, container, false);
        Button button = view.findViewById(R.id.mainbutton);
        button.setOnClickListener(v -> loadResultFragment());
        return view;
    }

    private void loadResultFragment() {
        EditText name = view.findViewById(R.id.edit_Name);
        EditText prenom = view.findViewById(R.id.edit_Surname);
        EditText age = view.findViewById(R.id.edit_Age);
        EditText numTel = view.findViewById(R.id.edit_NumTel);
        EditText email = view.findViewById(R.id.edit_email);
        EditText login = view.findViewById(R.id.edit_login);
        EditText mdp = view.findViewById(R.id.edit_password);
        CheckBox sport = view.findViewById(R.id.checkbox_sport);
        CheckBox musique = view.findViewById(R.id.checkbox_musique);
        CheckBox lecture = view.findViewById(R.id.checkbox_lecture);
        String interests = "";
        if (sport.isChecked()) interests += "sport,";
        if (musique.isChecked()) interests += "musique,";
        if (lecture.isChecked()) interests += "lecture,";
        if (interests.endsWith(",")) {
            interests = interests.substring(0, interests.length() - 1); // Remove trailing comma
        }
        if (!isValidLogin(login.getText().toString())) {
            Toast.makeText(getContext(), "Login invalide", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isLoginExist(login.getText().toString())) {
            Toast.makeText(getContext(), "Login déjà utilisé", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isValidPassword(mdp.getText().toString())) {
            Toast.makeText(getContext(), "Mot de passe invalide", Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues values = new ContentValues();
        values.put(DataSet.COLUMN_LOGIN, login.getText().toString());
        values.put(DataSet.COLUMN_PASSWORD, mdp.getText().toString());
        values.put(DataSet.COLUMN_NAME, name.getText().toString());
        values.put(DataSet.COLUMN_SURNAME, prenom.getText().toString());
        values.put(DataSet.COLUMN_BIRTH_DATE, age.getText().toString());
        values.put(DataSet.COLUMN_PHONE_NUMBER, numTel.getText().toString());
        values.put(DataSet.COLUMN_EMAIL, email.getText().toString());
        values.put(DataSet.COLUMN_INTERESTS, interests);
        SQLiteDatabase db = DataBase.getWritableDatabase();
        long newRowId = db.insert(DataSet.TABLE_USER_DATA, null, values);
        db.close();
        if (newRowId != -1) {
            Toast.makeText(getContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Failed to save data", Toast.LENGTH_SHORT).show();
        }
        Bundle bundle = new Bundle();
        bundle.putString("name", name.getText().toString());
        bundle.putString("prenom",prenom.getText().toString());
        bundle.putString("age",age.getText().toString());
        bundle.putString("numTel",numTel.getText().toString());
        bundle.putString("email",email.getText().toString());
        bundle.putString("login",login.getText().toString());
        bundle.putString("mdp",mdp.getText().toString());
        bundle.putBoolean("sport",sport.isChecked());
        bundle.putBoolean("musique",musique.isChecked());
        bundle.putBoolean("lecture",lecture.isChecked());
        // Create Exo1_2Fragment and set arguments
        exo1_2 exo1_2Fragment = new exo1_2();
        exo1_2Fragment.setArguments(bundle);
        // Replace the current fragment with Exo1_2Fragment
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame, exo1_2Fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private boolean isValidLogin(String login) {
        return login.matches("[a-zA-Z].{0,9}");
    }

    private boolean isValidPassword(String password) {
        return password.length()>=6;
    }
    private boolean isLoginExist(String login) {
        SQLiteDatabase db = DataBase.getReadableDatabase();
        Cursor cursor = db.query(DataSet.TABLE_USER_DATA, // Table
                new String[]{DataSet.COLUMN_LOGIN}, // Columns
                DataSet.COLUMN_LOGIN + " = ?", // Where clause
                new String[]{login}, // Where args
                null, // Group by
                null, // Having
                null); // Order by
        boolean exists = cursor.moveToFirst();
        cursor.close();
        db.close();
        return exists;
    }
}