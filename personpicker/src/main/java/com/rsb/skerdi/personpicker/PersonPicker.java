package com.rsb.skerdi.personpicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.rsb.skerdi.personpicker.DragDropFunctionality.DragStopListener;
import com.rsb.skerdi.personpicker.DragDropFunctionality.SDragListener;
import com.rsb.skerdi.personpicker.DragDropFunctionality.SLongClickListener;
import com.rsb.skerdi.personpicker.DragDropFunctionality.SToogleDragListener;
import com.rsb.skerdi.personpicker.DragDropFunctionality.STouchListener;
import com.squareup.picasso.Picasso;

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
    private Picasso picasso;
    private CardView cardView;
    private boolean toogleDynamic = true;
    private SDragListener sDragListenerPicker;
    private STouchListener sTouchListener;
    private SToogleDragListener sToogleDragListener;


    public PersonPicker(@NonNull Context context, ArrayList<Person> persons, ViewGroup viewGroup, PersonClickListener personClickListener) {
        super(context);

        this.persons = persons;
        this.context=context;
        this.layoutInflater  = LayoutInflater.from(context);
        this.viewGroup = viewGroup;
        this.personClickListener = personClickListener;
        picasso = Picasso.get();
        count = 0;
        view = layoutInflater.inflate(R.layout.person_picker,viewGroup,false);
        toogle_remainder = layoutInflater.inflate(R.layout.toogle, viewGroup, false);
        toogle_remainder.setVisibility(INVISIBLE);
        viewGroup.addView(toogle_remainder);
        viewGroup.addView(view);

        toogle = view.findViewById(R.id.toogle);
        cardView = view.findViewById(R.id.cardview);
        imageView = view.findViewById(R.id.imageview);
        emer = view.findViewById(R.id.emer);
        mbiemer = view.findViewById(R.id.mbiemer);

        sTouchListener = new STouchListener();
        sDragListenerPicker = new SDragListener(view,toogle_remainder,toogleDynamic);
        sToogleDragListener = new SToogleDragListener(view, toogle_remainder, toogleDynamic );

        toogle_remainder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View vieww) {
                view.setVisibility(VISIBLE);
                toogle_remainder.setVisibility(INVISIBLE);
               // ViewAnimator.animate(toogle_remainder).fadeOut().duration(1000).start();
                ViewAnimator.animate(view).translationX(0).duration(500).
                        start();
            }
        });

        toogle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View vieww) {
                // ViewAnimator.animate(view).bounceOut().start();
                toogle_remainder.setVisibility(VISIBLE);
                ViewAnimator.animate(toogle_remainder).fadeIn().duration(1000).start();
                ViewAnimator.animate(view).translationX(-width).duration(500).
                        start().onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        view.setVisibility(INVISIBLE);
                    }
                });

            }
        });


       view.setOnLongClickListener(new SLongClickListener());
       this.setOnDragListener(sDragListenerPicker);
       toogle_remainder.setOnLongClickListener(new SLongClickListener());

       toogle_remainder.setOnDragListener(sToogleDragListener);

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

        picasso.load(persons.get(selectedPersonId).getProfile_image_url()).into(imageView);
        emer.setText(persons.get(selectedPersonId).getName());
        mbiemer.setText(persons.get(selectedPersonId).getSurname());

        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Skerdi", "U shtyp fotoja");
                materialDialog.show();
            }
        });

        cardView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Skerdi", "U shtyp cardview");
                materialDialog.show();
            }
        });
    }

    @Override
    public void onBitmapDownloaded(Bitmap bitmap) {
        /*this.bitmap = bitmap;
        imageView.setImageBitmap(bitmap);
        invalidate();*/
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

    public void setToogleDynamic(boolean toogleDynamic){
        this.toogleDynamic = toogleDynamic;
        sToogleDragListener.setSynchroniseToogleWithPicker(toogleDynamic);
        sDragListenerPicker.setSynchroniseToogleWithPicker(toogleDynamic);
    }

    public boolean isToogleDynamic(){
        return this.toogleDynamic;
    }

}
