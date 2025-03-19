package com.example.tp3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class exo1_2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.exo1_2, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.getString("name");
            String prenom = bundle.getString("prenom");
            String age = bundle.getString("age");
            String numTel = bundle.getString("numTel");
            String email = bundle.getString("email");
            TextView textView = view.findViewById(R.id.name);
            textView.setText(name);
            textView = view.findViewById(R.id.prenom);
            textView.setText(prenom);
            textView = view.findViewById(R.id.DOB);
            textView.setText(age);
            textView = view.findViewById(R.id.NumTel);
            textView.setText(numTel);
            textView = view.findViewById(R.id.mail);
            textView.setText(email);
            textView = view.findViewById(R.id.login);
            textView.setText(bundle.getString("login"));
            textView = view.findViewById(R.id.mdp);
            textView.setText(bundle.getString("mdp"));
            if (bundle.getBoolean("sport")) {
                textView = view.findViewById(R.id.sport);
                textView.setText("Sport : Oui");
            } else {
                textView = view.findViewById(R.id.sport);
                textView.setText("Sport : Non");
            }
            if (bundle.getBoolean("musique")) {
                textView = view.findViewById(R.id.musique);
                textView.setText("Musique : Oui");
            } else {
                textView = view.findViewById(R.id.musique);
                textView.setText("Musique : Non");
            }
            if (bundle.getBoolean("lecture")) {
                textView = view.findViewById(R.id.lecture);
                textView.setText("Lecture : Oui");
            } else {
                textView = view.findViewById(R.id.lecture);
                textView.setText("Lecture : Non");
            }
        }
        return view;
    }
}