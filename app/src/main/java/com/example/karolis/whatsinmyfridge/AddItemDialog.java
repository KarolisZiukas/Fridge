package com.example.karolis.whatsinmyfridge;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AddItemDialog extends DialogFragment{

    @BindView(R.id.add_item_name_edit_text) EditText addItemNameEditText;
    @BindView(R.id.set_expiration_date_button) Button setExpirationDateButton;
    @BindView(R.id.set_quantity_seek_bar) SeekBar setQuantitySeekBar;
    @BindView(R.id.add_item_button) Button addItemButton;
    @BindView(R.id.add_image_image_button) ImageButton addImageImageButton;
    @BindView(R.id.dialog_expiration_date_text_view) TextView dialogExpirationDateTextView;
    @BindView(R.id.dialog_quantity_text_view) TextView dialogQuantityTextView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private final Calendar calendar = Calendar.getInstance();
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Bitmap bitmapItemImage;
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);
            setExpirationDateButton.setText(simpleDateFormat.format(calendar.getTime()));

        }
    };



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_item_dialog, container, false);
        ButterKnife.bind(this, view);
        InitialValues();
        addImageImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 0);
            }
        });


        setExpirationDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new DatePickerDialog(getContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        setQuantitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                dialogQuantityTextView.setText("" + i/10);
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
                foodItemModel.setExpirationdate(simpleDateFormat.format(calendar.getTime()));
                foodItemModel.setQuantity(setQuantitySeekBar.getProgress() / 10);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                if (bitmapItemImage != null){
                    bitmapItemImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    foodItemModel.setImageByteArray(stream.toByteArray());
            }
                ((DialogCallbackContract)getTargetFragment()).passData(foodItemModel);
                dismiss();
            }
        });

        return view;

    }

    public void InitialValues(){
        setExpirationDateButton.setText(simpleDateFormat.format(new Date()));
        dialogQuantityTextView.setText("" + setQuantitySeekBar.getProgress()/10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle itemImageBundle = data.getExtras();
            bitmapItemImage = (Bitmap) itemImageBundle.get("data");
            addImageImageButton.setImageBitmap(bitmapItemImage);
        }
    }
}
