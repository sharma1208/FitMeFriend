
package com.example.fitmefriend.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitmefriend.OutfitSwipeActivity;
import com.example.fitmefriend.R;
import com.example.fitmefriend.Pants;

import java.util.List;

public class PantsAdapter extends RecyclerView.Adapter<PantsAdapter.PantsViewHolder> {
    private List<Pants> pantsList;
    private Context context;



    public PantsAdapter(List<Pants> pantsList, Context context) {
        this.pantsList = pantsList;
        this.context = context;
    }

    @NonNull
    @Override
    public PantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pant_layout, parent , false);
        return new PantsViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull PantsViewHolder holder, int position) {
        Pants p= pantsList.get(position);
        holder.mImageView.setImageResource(Integer.parseInt(p.getpImageResourceId()));

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



    public List<Pants> getPantsList() {
        return pantsList;
    }

    public void setPantsList(List<Pants> pantsList) {
        this.pantsList = pantsList;
    }
}

