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
import com.example.fitmefriend.Outfits;
import com.example.fitmefriend.Pants;
import com.example.fitmefriend.R;

import java.util.ArrayList;

public class OutfitsAdapter extends RecyclerView.Adapter<OutfitsAdapter.OutfitsViewHolder>{
    ArrayList<Outfits> outfitList;
    private Context context;

    public OutfitsAdapter(ArrayList<Outfits> outfitList, Context context) {
        this.outfitList = outfitList;
        this.context = context;
    }
    @NonNull
    @Override
    public OutfitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.outfit_row, parent , false);
        return new OutfitsViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull OutfitsAdapter.OutfitsViewHolder holder, int position) {
        Outfits o = outfitList.get(position);
        //holder.sImageView.setImageResource(Integer.parseInt((o.getS().getImageResourceId())));
        // holder.pImageView.setImageResource(Integer.parseInt(o.getP().getpImageResourceId()));

        Log.i("What is", "Image p: " + o.getP().getpImageResourceId());
        Log.i("What is", "Image s: " + o.getS().getImageResourceId());

        Glide.with(context).load(o.getP().getpImageResourceId()).placeholder(R.drawable.ic_baseline_favorite_24).
                error(R.drawable.ic_baseline_favorite_border_24).override(200,300).into(holder.pImageView);
        Glide.with(context).load(o.getS().getImageResourceId()).placeholder(R.drawable.ic_baseline_favorite_24).
                error(R.drawable.ic_baseline_favorite_border_24).override(200,300).into(holder.sImageView);
    }


    @Override
    public int getItemCount() {
        return outfitList.size();
    }

    public class OutfitsViewHolder extends RecyclerView.ViewHolder{
        private ImageView sImageView;
        private ImageView pImageView;
        public OutfitsViewHolder(@NonNull View itemView){
            super(itemView);


            sImageView = itemView.findViewById(R.id.shirt);
            pImageView = itemView.findViewById(R.id.pant);

        }
    }

    public ArrayList<Outfits> getOutfitList() {
        return outfitList;
    }

    public void setOutfitList(ArrayList<Outfits> outfitList) {
        this.outfitList = outfitList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
