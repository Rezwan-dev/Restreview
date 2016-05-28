package com.example.rezwan.letsmakeithappen;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private AppBarLayout htab_appbar;
    private View ref;
    private CollapsingToolbarLayout htab_collapse_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.htab_tabs);
        htab_appbar = (AppBarLayout) findViewById(R.id.htab_appbar);
        ref = findViewById(R.id.ref);
        htab_collapse_toolbar = (CollapsingToolbarLayout)findViewById(R.id.htab_collapse_toolbar);
        htab_appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int maxScroll = appBarLayout.getTotalScrollRange();
                float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                ref.setAlpha(1-percentage);
                if( percentage < 1){
                    htab_collapse_toolbar.setTitle("");
                }else{
                    htab_collapse_toolbar.setTitle("Tasty Tummy");
                }
            }
        });
        viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        setupViewPager(viewPager);
        assert tabLayout != null;
        tabLayout.setupWithViewPager(viewPager);
       /* tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });*/
        ((BlurView)findViewById(R.id.blurView)).setBlurredView(findViewById(R.id.htab_header));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new InfoFrag(), "INFO");
        adapter.addFrag(new DummyFragment(), "MENU");
        adapter.addFrag(new ReviewFrag(), "REVIEWS");
        viewPager.setAdapter(adapter);
    }



    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

  /*  Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
        @SuppressWarnings("ResourceType")
        @Override
        public void onGenerated(Palette palette) {
            int vibrantColor = palette.getVibrantColor(R.color.primary_500);
            int vibrantDarkColor = palette.getDarkVibrantColor(R.color.primary_700);
            collapsingToolbarLayout.setContentScrimColor(vibrantColor);
            collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
        }
    });*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (!((htab_appbar.getHeight() - htab_appbar.getBottom()) == 0)) {
                htab_appbar.setExpanded(true);
                htab_appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if (verticalOffset == 0) {
                            supportFinishAfterTransition();
                        }
                    }
                });
            } else {
                supportFinishAfterTransition();

            /*htab_appbar.setExpanded(true);
            supportFinishAfterTransition();*/
            }
            return true;
        }
        if(item.getItemId() == R.id.nav_write_review){
            WriteReviewFrag dialogFragment =  new WriteReviewFrag();
            dialogFragment.show(getSupportFragmentManager(),"Review");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (!((htab_appbar.getHeight() - htab_appbar.getBottom()) == 0)) {
            htab_appbar.setExpanded(true);
            htab_appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (verticalOffset == 0) {
                        DetailActivity.super.onBackPressed();
                    }
                }
            });
        } else {
            DetailActivity.super.onBackPressed();

            /*htab_appbar.setExpanded(true);
            supportFinishAfterTransition();*/
        }
    }
}
