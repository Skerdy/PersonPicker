package com.rsb.skerdi.personpicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 7/31/2018.
 */

public class PersonPicker extends View implements BitmapDownloadAssistant {

    private boolean bitmapdownloaded = false;

    private ArrayList<Person> persons;
    private int count;
    private int selectedPersonId;
    private Bitmap bitmap;
    private View view;
    private Context context;
    private LayoutInflater layoutInflater;
    private ViewGroup viewGroup;
    private ImageView toogle, imageView;
    private TextView emer, mbiemer;


    public PersonPicker(@NonNull Context context, ArrayList<Person> persons, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(context);

        Log.d("Skerdi", "Konstruktori i PersonPicker");
        this.persons = persons;
        this.context=context;
        this.layoutInflater = layoutInflater;
        this.viewGroup = viewGroup;
        count = 0;

        for(Person person : persons){
            person.setId(count);
            count++;
        }
        if(persons.size()>0)
        selectedPersonId = persons.get(0).getId();
        else{
            throw new IllegalArgumentException("You have to insert a nonempty list of Persons!");
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        view = layoutInflater.inflate(R.layout.person_picker,viewGroup,false);

        viewGroup.addView(view);
        toogle = view.findViewById(R.id.toogle);
        imageView = view.findViewById(R.id.imageview);
        emer = view.findViewById(R.id.emer);
        mbiemer = view.findViewById(R.id.mbiemer);


        if(bitmap!=null){
            Log.d("Skerdi", "Po vizatohet imazhi");
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            paint.setDither(true);
            imageView.setImageBitmap(bitmap);
            emer.setText(persons.get(selectedPersonId).getName());
            mbiemer.setText(persons.get(selectedPersonId).getSurname());

           // canvas.drawBitmap(bitmap, 10, 25, paint);

        }
        else {
            if(persons.get(selectedPersonId).getProfile_image_url()!=null){
                new GetBitmapsAsync(this).execute(persons.get(selectedPersonId).getProfile_image_url());
            }
            Log.d("Skerdi", "Po vizatohet teksti");
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);

            paint.setColor(Color.BLACK);
            paint.setTextSize(20);
            canvas.drawText("There is no image", 100, 250, paint);

        }
    }

    @Override
    public void onBitmapDownloaded(Bitmap bitmap) {
        this.bitmap = bitmap;
        invalidate();
    }
}
