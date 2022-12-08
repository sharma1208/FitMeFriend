
package com.example.fitmefriend.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fitmefriend.OutfitSwipeActivity;
import com.example.fitmefriend.Pants;
import com.example.fitmefriend.R;
import com.example.fitmefriend.Shirts;
import com.example.fitmefriend.UploadActivity;

import java.util.ArrayList;
import java.util.List;

public class ShirtsAdapter extends RecyclerView.Adapter<ShirtsAdapter.ShirtsViewHolder> {

  ArrayList<Shirts> shirtsList;
    private Context context;

    public ShirtsAdapter(ArrayList<Shirts> shirtsList, Context context) {
        this.shirtsList = shirtsList;
        this.context = context;
    }
    @NonNull
    @Override
    public ShirtsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shirt_layout, parent , false);
        return new ShirtsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShirtsViewHolder holder, int position) {
        Shirts s= shirtsList.get(position);
        //holder.mImageView.setImageResource(Integer.parseInt(p.getpImageResourceId()));
        Log.i("Scary",s.getImageResourceId());
        Glide.with(context).load(s.getImageResourceId()).placeholder(R.drawable.ic_baseline_favorite_24).
                error(R.drawable.ic_baseline_favorite_border_24).centerCrop().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return shirtsList.size();
    }

    public class ShirtsViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;
        public ShirtsViewHolder(@NonNull View itemView){
            super(itemView);


            mImageView = itemView.findViewById(R.id.bestSellerImage);
        }
    }
}



