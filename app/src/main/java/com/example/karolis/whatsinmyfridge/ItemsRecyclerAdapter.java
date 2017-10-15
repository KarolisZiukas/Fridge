package com.example.karolis.whatsinmyfridge;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.ItemViewHolder> {

    List<TempModel> tempModels;

    public ItemsRecyclerAdapter(List<TempModel> tempModels){
        this.tempModels = tempModels;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.itemNameTextView.setText(tempModels.get(0).getName());
        holder.itemExpirationTextView.setText(tempModels.get(0).getData());

    }

    @Override
    public int getItemCount() {
        return tempModels.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_photo_image_view) ImageView itemPhotoImageView;
        @BindView(R.id.item_name_text_view) TextView itemNameTextView;
        @BindView(R.id.item_expiration_text_view) TextView itemExpirationTextView;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
