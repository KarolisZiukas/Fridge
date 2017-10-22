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

    List<FoodItemModel> foodItemModels;
    private final RemoveItemOnClickListener removeItemOnClickListener;

    public ItemsRecyclerAdapter(List<FoodItemModel> foodItemModels,  RemoveItemOnClickListener removeItemOnClickListener){
        this.foodItemModels = foodItemModels;
        this.removeItemOnClickListener = removeItemOnClickListener;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.itemNameTextView.setText(foodItemModels.get(position).getName());
        holder.itemExpirationTextView.setText(foodItemModels.get(position).getExpirationdate());

        holder.removeItemImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItemOnClickListener.onItemClick(foodItemModels.get(holder.getAdapterPosition()));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItemModels.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_photo_image_view) ImageView itemPhotoImageView;
        @BindView(R.id.item_name_text_view) TextView itemNameTextView;
        @BindView(R.id.item_expiration_text_view) TextView itemExpirationTextView;
        @BindView(R.id.remove_item_image_view) ImageView removeItemImageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface RemoveItemOnClickListener {
        void onItemClick(FoodItemModel foodItemModel);
    }


}
