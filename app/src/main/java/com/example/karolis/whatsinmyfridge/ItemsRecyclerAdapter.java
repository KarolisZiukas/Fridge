package com.example.karolis.whatsinmyfridge;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.ItemViewHolder> {

    RealmResults<FoodItemModel> foodItemModels;
    private final RemoveItemOnClickListener removeItemOnClickListener;

    public ItemsRecyclerAdapter(RealmResults<FoodItemModel> foodItemModels, RemoveItemOnClickListener removeItemOnClickListener){
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
        holder.quantityTextView.setText("" + foodItemModels.get(position).getQuantity());

        Bitmap bitmap = BitmapFactory.decodeByteArray(foodItemModels.get(position).getImageByteArray(), 0, foodItemModels.get(position).getImageByteArray().length);
        holder.itemPhotoImageView.setImageBitmap(bitmap);

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
        @BindView(R.id.quantity_text_view) TextView quantityTextView;
        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    interface RemoveItemOnClickListener {
        void onItemClick(FoodItemModel foodItemModel);
    }


}
