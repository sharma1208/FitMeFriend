package com.example.fitmefriend.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitmefriend.R;
import com.example.fitmefriend.Shirts;

import java.util.List;

public class ShirtsAdapter extends RecyclerView.Adapter<ShirtsAdapter.ShirtsViewHolder> {

    private List<Shirts> shirtsList;
    public ShirtsAdapter(List<Shirts> shirtsList){
        this.shirtsList = shirtsList;
    }
    @NonNull
    @Override
    public ShirtsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shirt_layout, parent , false);
        return new ShirtsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShirtsViewHolder holder, int position) {
        holder.mImageView.setImageResource(shirtsList.get(position).getImageResourceId());
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