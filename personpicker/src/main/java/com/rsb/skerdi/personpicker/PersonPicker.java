package com.rsb.skerdi.personpicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.florent37.viewanimator.ViewAnimator;

import java.util.ArrayList;

/**
 * Created by user on 7/31/2018.
 */

public class PersonPicker extends View implements BitmapDownloadAssistant, PersonClickListener {

    private boolean bitmapdownloaded = false;

    private ArrayList<Person> persons;
    private int count;
    private int selectedPersonId;
    private Bitmap bitmap;
    private View view, person_list, toogle_remainder;
    private Context context;
    private LayoutInflater layoutInflater;
    private ViewGroup viewGroup;
    private ImageView toogle, imageView;
    private TextView emer, mbiemer;
    private RecyclerView recyclerView;
    private MaterialDialog materialDialog;
    private PersonAdapter personAdapter;
    private PersonClickListener personClickListener;
    private int width = 0;


    public PersonPicker(@NonNull Context context, ArrayList<Person> persons, ViewGroup viewGroup, PersonClickListener personClickListener) {
        super(context);
        Log.d("Skerdi", "Konstruktori i PersonPicker");
        this.persons = persons;
        this.context=context;
        this.layoutInflater  = LayoutInflater.from(context);
        this.viewGroup = viewGroup;
        this.personClickListener = personClickListener;
        count = 0;
        view = layoutInflater.inflate(R.layout.person_picker,viewGroup,false);
        toogle_remainder = layoutInflater.inflate(R.layout.toogle, viewGroup, false);
        toogle_remainder.setVisibility(GONE);
        viewGroup.addView(toogle_remainder);
        viewGroup.addView(view);

        toogle_remainder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View vieww) {
                ViewAnimator.animate(toogle_remainder).fadeOut().duration(1000).start();
                ViewAnimator.animate(view).translationX(0).accelerate().duration(500).
                        start();
            }
        });

        materialDialog = new MaterialDialog.Builder(context).title("Pick the Person")
                .customView(R.layout.persons_list,false).build();

        recyclerView = materialDialog.getCustomView().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        personAdapter = new PersonAdapter(context, persons, this);
        recyclerView.setAdapter(personAdapter);



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



        toogle = view.findViewById(R.id.toogle);

        toogle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View vieww) {
               // ViewAnimator.animate(view).bounceOut().start();
                toogle_remainder.setVisibility(VISIBLE);
                ViewAnimator.animate(toogle_remainder).fadeIn().duration(1000).start();
                ViewAnimator.animate(view).translationX(-width).accelerate().duration(500).
                       start();
            }
        });
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
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Skerdi", "U shtyp fotoja");

                    materialDialog.show();
                }
            });

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
        imageView.setImageBitmap(bitmap);
        invalidate();
    }

    @Override
    public void onPersonClicked(int position) {
        personClickListener.onPersonClicked(position);
        selectedPersonId = position;
        materialDialog.hide();
        bitmap = null;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("Skerdi", "width = " + view.getMeasuredWidth());
        width = view.getMeasuredWidth();
    }

}
