package com.example.karolis.whatsinmyfridge;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.add_item_action_button) FloatingActionButton addItemActionButton;
    @BindView(R.id.navigation_slider_view) NavigationView navigationDrawerView;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;


    private NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment( new ItemsListFragment());
                    drawerLayout.closeDrawers();
                    return true;
                case R.id.navigation_schedule:
                    loadFragment( new ItemDetailsFragment());
                    drawerLayout.closeDrawers();
                    return true;
                case R.id.navigation_reminders:
                    loadFragment( new ItemsListFragment());
                    drawerLayout.closeDrawers();
                    return true;
            }
            return true;

        }
    };


    public Fragment itemsListFragment;
    //ToDo shopping list
    //ToDo categories
    //Todo Search
    //ToDo edit info
//ToDo reminder before expiring
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsListFragment = new ItemsListFragment();
        ButterKnife.bind(MainActivity.this);
        loadActionBar();
    }

    private void loadActionBar() {
        navigationDrawerView.getMenu().getItem(0).setChecked(true);
        navigationDrawerView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);
        navigationDrawerView.bringToFront();
        navigationDrawerView.requestLayout();
    }




    public void loadFragment(Fragment fragment){
        itemsListFragment = fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout, fragment);
        fragmentTransaction.commit();
        addItemActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogShow();
            }
        });
    }


    public void dialogShow() {
        DialogFragment fragment = new AddItemDialog();
        fragment.setTargetFragment(itemsListFragment, 0);
        fragment.show(getSupportFragmentManager(), "tag");
    }



}
