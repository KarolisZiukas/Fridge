package com.example.karolis.whatsinmyfridge;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemsListFragment extends Fragment implements DialogCallbackContract, ItemsRecyclerAdapter.RemoveItemOnClickListener{

    @BindView(R.id.items_recycler_view) RecyclerView itemsRecyclerView;
    List<FoodItemModel> foodItemModels = new ArrayList<FoodItemModel>();
    ItemsRecyclerAdapter itemsRecyclerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.items_recycler_vew, container, false);
        ButterKnife.bind(this, view);
        itemsRecyclerView.setHasFixedSize(true);
        itemsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        FoodItemModel a = new FoodItemModel();
        a.setExpirationdate("today");
        a.setName("pepper");
        a.setQuantity(5);
        FoodItemModel b = new FoodItemModel();
        b.setExpirationdate("today");
        b.setName("pepper");
        b.setQuantity(5);
        foodItemModels.add(a);
        foodItemModels.add(b);

        itemsRecyclerAdapter = new ItemsRecyclerAdapter(foodItemModels, this);

        itemsRecyclerView.setAdapter(itemsRecyclerAdapter);
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        Toast.makeText(getContext(), "resumed", Toast.LENGTH_LONG).show();
        super.onResume();
    }

    public void addItem(FoodItemModel foodItemModel){
        foodItemModels.add(foodItemModel);
        itemsRecyclerAdapter.notifyDataSetChanged();
    }


    @Override
    public void passData(FoodItemModel foodItemModel) {
        foodItemModels.add(foodItemModel);
        itemsRecyclerAdapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "pass", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(FoodItemModel foodItemModel) {
        foodItemModels.remove(foodItemModel);
    }
}
