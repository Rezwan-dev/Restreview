package com.example.rezwan.letsmakeithappen;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;

import java.util.ArrayList;

public class Nearby extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Activity activity;
    private FloatingActionButton fab;
    private FABToolbarLayout fabtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_nearby);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fabtoolbar = (FABToolbarLayout)findViewById(R.id.fabtoolbar);

        fab = (FloatingActionButton) findViewById(R.id.fabtoolbar_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabtoolbar.show();
                final View ET  = fabtoolbar.findViewById(R.id.searchET);
                ET.requestFocus();
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        ET.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN , 0, 0, 0));
                        ET.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP , 0, 0, 0));

                    }
                }, 800);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ArrayList<String> items = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            items.add("String "+i);
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        SimpleStringRecyclerViewAdapter simpleStringRecyclerViewAdapter = new SimpleStringRecyclerViewAdapter(items, this);
        recyclerView.setAdapter(simpleStringRecyclerViewAdapter);
        View headerView = LayoutInflater.from(this).inflate(R.layout.header_main, null, false);
        ((BlurView)headerView.findViewById(R.id.blurView)).setBlurredView(headerView.findViewById(R.id.pro_img));
        recyclerView.addItemDecoration(HeaderDecoration.with(recyclerView)
                .setView(headerView)
                .parallax(0.1f)
                .dropShadowDp(0)
                .build());
    }

    public View getRootView(){
        return findViewById(android.R.id.content);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START) || fabtoolbar.isShown()) {
            drawer.closeDrawer(GravityCompat.START);
            fabtoolbar.hide();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nearby, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_filter) {
            FragmentManager fm =getSupportFragmentManager();
            FilterFragment filterFragment = new FilterFragment();
            filterFragment.setStyle( DialogFragment.STYLE_NORMAL, R.style.CustomDialog );
            filterFragment.show(fm, null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_create_account:{
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_sign_in:{
                Intent intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;
            }
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        private ArrayList<String> mValues;
        private Activity activity;

        public static class ViewHolder1 extends RecyclerView.ViewHolder {

            public final View rootView;

            public ViewHolder1(View view) {
                super(view);
                rootView = view;

            }


        }


        public SimpleStringRecyclerViewAdapter(ArrayList<String> items, Activity activity) {
            mValues = items;
            this.activity = activity;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder;
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.search_single_item_without_more, parent, false);
            viewHolder = new ViewHolder1(view);
            return viewHolder;

        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            final ViewHolder1 vh1 = (ViewHolder1) holder;
            vh1.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    //Pair<View, String> p1 = Pair.create(v.findViewById(R.id.pro_img), "test1");
                    Pair<View, String> p2 = Pair.create(v.findViewById(R.id.name_tv), "test2");
                    Pair<View, String> p3 = Pair.create(v.findViewById(R.id.ratingBar), "test3");
                    Pair<View, String> p4 = Pair.create(v.findViewById(R.id.loc_tv), "test4");
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(activity, p2,p3, p4);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        activity.startActivity(intent, options.toBundle());
                    }else{
                        activity.startActivity(intent);
                    }
                }
            });
        }
        @Override
        public int getItemCount() {
            return mValues.size();
        }

    }


}
