package com.example.myfilm.recommend;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myfilm.R;
import com.example.myfilm.recommend.Recommend;

public class Views extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView Name, Desc;
        public ImageView Image;
    public Views(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            Name = (TextView) itemView.findViewById(R.id.Name);
            Image = (ImageView) itemView.findViewById(R.id.Image);
        Desc = (TextView) itemView.findViewById(R.id.Desc);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), Recommend.class);
            view.getContext().startActivity(intent);
        }
    }
