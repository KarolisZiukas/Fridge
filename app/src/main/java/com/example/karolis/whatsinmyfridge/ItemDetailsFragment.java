package com.example.karolis.whatsinmyfridge;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

public class ItemDetailsFragment extends Fragment {
    @BindView(R.id.item_photo_image_view) ImageView itemDetailsImageView;
    @BindView(R.id.item_name_text_view) TextView itemDetailsNameTextView;
    @BindView(R.id.item_expiration_text_view) TextView itemDetailsExpirationTextView;
    @BindView(R.id.item_details_quantity_text_view) TextView itemDetailsQuantityTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_details_fragment, container, false);
        return view;
    }
}
