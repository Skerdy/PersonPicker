package com.rsb.skerdi.librarytestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rsb.skerdi.personpicker.DragDropFunctionality.SDragListener;
import com.rsb.skerdi.personpicker.Person;
import com.rsb.skerdi.personpicker.PersonClickListener;
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
        persons.add(new Person("Jashar", "Xhika", "https://z-p3-scontent.fath5-1.fna.fbcdn.net/v/t1.0-9/15203404_1331820053535824_6544001316368202859_n.jpg?_nc_cat=0&oh=8e3c09c64ff0539fb32c830351cb33d4&oe=5C02B40E"));
        persons.add(new Person("Luan", "Gurabardhi", "https://z-p3-scontent.fath5-1.fna.fbcdn.net/v/t1.0-9/29598116_1419569344838305_3586080690766352165_n.jpg?_nc_cat=0&oh=0589bd798c83f89ae49789c47909e0f8&oe=5C0F0886"));


        PersonPicker personPicker = new PersonPicker(this, persons, frameLayout, new PersonClickListener() {
            @Override
            public void onPersonClicked(int position) {
                Toast.makeText(MainActivity.this,"Clicked position " + position, Toast.LENGTH_LONG).show();
            }
        });
        personPicker.setToogleDynamic(true);
        frameLayout.addView(personPicker);

    }
}
