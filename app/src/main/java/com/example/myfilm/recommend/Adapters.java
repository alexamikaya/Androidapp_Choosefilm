package com.example.myfilm.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfilm.R;

import java.util.List;

public class Adapters extends RecyclerView.Adapter<Views>{
    private List<Objects> List;
    private Context context;


    public Adapters(List<Objects> List, Context context){
        this.List = List;
        this.context = context;
    }

    @Override
    public Views onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        Views rcv = new Views(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(Views holder, int position) {

        holder.Name.setText(List.get(position).getName());
        holder.Desc.setText(List.get(position).getdesc());
        if(!List.get(position).getimage().equals("default")){
            Glide.with(context).load(List.get(position).getimage()).into(holder.Image);
        }
    }

    @Override
    public int getItemCount() {
        return this.List.size();
    }
}