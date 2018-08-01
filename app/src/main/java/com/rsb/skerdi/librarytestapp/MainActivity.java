package com.rsb.skerdi.librarytestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.rsb.skerdi.personpicker.Person;
import com.rsb.skerdi.personpicker.PersonPicker;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout frameLayout;

    private ArrayList<Person> persons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.layout);
        persons = new ArrayList<>();
        persons.add(new Person("Skerdi", "Gurabardhi", "https://z-p3-scontent.fath5-1.fna.fbcdn.net/v/t1.0-0/p370x247/16681667_1378333725550492_7645852226125849371_n.jpg?_nc_cat=0&oh=e0e884b54e438cda7dc922153e016027&oe=5BC7ADDD"));


        Log.d("Skerdi", "Po krijohet view e re");
        PersonPicker personPicker = new PersonPicker(this, persons, getLayoutInflater(), frameLayout );
        frameLayout.addView(personPicker);
        Log.d("Skerdi", "View e re u shtua");
    }
}
