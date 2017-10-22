package com.example.karolis.whatsinmyfridge;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddItemDialog extends DialogFragment{

    @BindView(R.id.add_item_name_edit_text) EditText addItemNameEditText;
    @BindView(R.id.set_expiration_date_button) Button setExpirationDateButton;
    @BindView(R.id.set_quantity_seek_bar) SeekBar setQuantitySeekBar;
    @BindView(R.id.add_item_button) Button addItemButton;
    private int expirationDate;
    private int quantity;
    private String name;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_item_dialog, container, false);
        ButterKnife.bind(this, view);
        DialogCallbackContract dialogCallbackContract = (DialogCallbackContract)getTargetFragment();
        Toast.makeText(getContext(), addItemNameEditText.getText(), Toast.LENGTH_LONG).show();
        setExpirationDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        setQuantitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                quantity = i/10;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodItemModel foodItemModel = new FoodItemModel();
                foodItemModel.setName(addItemNameEditText.getText().toString());
                foodItemModel.setExpirationdate("date");
                foodItemModel.setQuantity(quantity);
                ((DialogCallbackContract)getTargetFragment()).passData(foodItemModel);
                dismiss();
            }
        });

        return view;

    }

}
