
package com.example.fitmefriend.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitmefriend.R;
import com.example.fitmefriend.Pants;

import java.util.List;

public class PantsAdapter extends RecyclerView.Adapter<PantsAdapter.PantsViewHolder> {

    private List<Pants> pantsList;
    public PantsAdapter(List<Pants> pantsList){
        this.pantsList = pantsList;
    }
    @NonNull
    @Override
    public PantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pant_layout, parent , false);
        return new PantsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PantsViewHolder holder, int position) {
        int id= Integer.parseInt(pantsList.get(position).getpImageResourceId());
        holder.mImageView.setImageResource(id);
    }

    @Override
    public int getItemCount() {
        return pantsList.size();
    }

    public class PantsViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;
        public PantsViewHolder(@NonNull View itemView){
            super(itemView);


            mImageView = itemView.findViewById(R.id.bestSellerImage);
        }
    }
}

