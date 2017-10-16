package com.example.karolis.whatsinmyfridge;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddItemDialog extends DialogFragment{

    @BindView(R.id.add_item_name_edit_text) EditText addItemNameEditText;
    @BindView(R.id.set_expiration_date_button) Button setExpirationDateButton;
    @BindView(R.id.set_quantity_seek_bar) SeekBar setQuantitySeekBar;
    @BindView(R.id.add_item_button) Button addItemButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_item_dialog, container, false);
        ButterKnife.bind(this, view);
        setExpirationDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });
        return view;
    }
}
