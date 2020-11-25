package com.example.onlinequizapp;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Random;

public class SetAdapter extends BaseAdapter {
    private int numofsets;

    public SetAdapter(int numofsets) {
        this.numofsets = numofsets;
    }

    @Override
    public int getCount() {
        return numofsets;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view;
        if (convertView==null)
        {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item_layout,parent,false);
        }

        else
        {
            view=convertView;
        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(parent.getContext(),QuestionActivity.class);
                parent.getContext().startActivity(intent);
            }
        });

        ((TextView)view.findViewById(R.id.setname)).setText(String.valueOf(position+1));



        return view;


    }
}
