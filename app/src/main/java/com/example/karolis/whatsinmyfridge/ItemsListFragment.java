package com.example.karolis.whatsinmyfridge;


import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemsListFragment extends Fragment{

    @BindView(R.id.items_recycler_view) RecyclerView itemsRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.items_recycler_vew, container, false);
        ButterKnife.bind(this, view);
        itemsRecyclerView.setHasFixedSize(true);
        itemsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        List<TempModel> tempModels = new ArrayList<TempModel>();
        TempModel a = new TempModel();
        a.setData("today");
        a.setName("pepper");
        tempModels.add(a);
        itemsRecyclerView.setAdapter(new ItemsRecyclerAdapter(tempModels));
        return view;

    }
}
