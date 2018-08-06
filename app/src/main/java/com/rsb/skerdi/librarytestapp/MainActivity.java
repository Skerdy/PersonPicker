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
        persons.add(new Person("Mark", "Zuckerberg", "https://scontent.ftia5-1.fna.fbcdn.net/v/t34.0-12/16176889_112685309244626_578204711_n.jpg?_nc_cat=0&oh=2994308a4c27c065cd5c2d5b4ede8f56&oe=5B6A7928"));
        persons.add(new Person("Bill", "Gates", "https://scontent.ftia5-1.fna.fbcdn.net/v/t1.0-9/23473074_10155031875776961_8482140412038626648_n.jpg?_nc_cat=0&oh=dca693e31d614b03d2a8a8f1c724f52d&oe=5C064B06"));
        persons.add(new Person("Steve", "Jobs", "https://scontent.ftia5-1.fna.fbcdn.net/v/t1.0-9/380250_308540849159158_2094417307_n.jpg?_nc_cat=0&oh=84c2dae64d74f501407c8c5b5daa0f3f&oe=5C0BBB31"));
        persons.add(new Person("Elon", "Musk", "https://scontent.ftia5-1.fna.fbcdn.net/v/t1.0-9/36650939_117467132499850_5479383082823843840_n.jpg?_nc_cat=0&oh=901dc5415f5f6701a7b2bc6892818011&oe=5C048B43"));
        persons.add(new Person("Mia", "Khalifa", "https://scontent.ftia5-1.fna.fbcdn.net/v/t1.0-9/27972544_240766439798196_1114524023250093741_n.jpg?_nc_cat=0&oh=a65f8eb1f375c8ea8b8788c34a5fba17&oe=5C108913"));

        PersonPicker personPicker = new PersonPicker(this, persons, frameLayout, new PersonClickListener() {

            @Override
            public void onPersonClicked(int position) {
                Toast.makeText(MainActivity.this,"Clicked position " + position, Toast.LENGTH_LONG).show();
            }
        });
        personPicker.setToogleDynamic(false);
        frameLayout.addView(personPicker);

    }
}
