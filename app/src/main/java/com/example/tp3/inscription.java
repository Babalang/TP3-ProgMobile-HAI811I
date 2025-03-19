package com.example.tp3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class inscription extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState)	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_activity);
        loadFormFragment();

    }

    private void loadFormFragment() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
        exo1 countryListFragment = new exo1();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame, countryListFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
