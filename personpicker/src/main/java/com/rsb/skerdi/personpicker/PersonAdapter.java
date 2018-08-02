package com.rsb.skerdi.personpicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by user on 8/2/2018.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Person> persons;
    private PersonClickListener personClickListener;

    public PersonAdapter(Context context,ArrayList<Person> persons, PersonClickListener personClickListener){
        this.context = context;
        this.persons = persons;
        this.personClickListener = personClickListener;
    }


    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.person_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.emri.setText( persons.get(position).getName());
        holder.mbiemri.setText(persons.get(position).getSurname());
        Picasso.get().load(persons.get(position).getProfile_image_url()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personClickListener.onPersonClicked(position);
            }
        });
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personClickListener.onPersonClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView emri, mbiemri;
        private ImageView imageView;
        private RelativeLayout relativeLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            emri = itemView.findViewById(R.id.name);
            mbiemri = itemView.findViewById(R.id.surname);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
            imageView = itemView.findViewById(R.id.person_image);
        }
    }
}
