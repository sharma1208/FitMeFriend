
package com.example.fitmefriend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitmefriend.OutfitSwipeActivity;
import com.example.fitmefriend.Pants;
import com.example.fitmefriend.R;
import com.example.fitmefriend.Shirts;

import java.util.ArrayList;
import java.util.List;

public class ShirtsAdapter extends RecyclerView.Adapter<ShirtsAdapter.ShirtsViewHolder> {
    private Context context;
    public List<Shirts> shirtsList;

    public ShirtsAdapter(List<Shirts> shirtsList, Context context) {
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

        int id= Integer.parseInt(shirtsList.get(position).getImageResourceId());
        holder.mImageView.setImageResource(id);
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



