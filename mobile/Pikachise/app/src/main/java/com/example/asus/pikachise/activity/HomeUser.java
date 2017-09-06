package com.example.asus.pikachise.activity;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.asus.pikachise.R;
import com.example.asus.pikachise.adapter.HomeUserVPAdapter;
import com.example.asus.pikachise.fragment.DummyFourFragment;
import com.example.asus.pikachise.fragment.DummyOneFragment;
import com.example.asus.pikachise.fragment.DummyThreeFragment;
import com.example.asus.pikachise.fragment.DummyTwoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeUser extends AppCompatActivity {

    @BindView(R.id.homeuser_drawerlayout) DrawerLayout drawerLayout;
    @BindView(R.id.homeuser_navigationview) NavigationView navigationView;
    @BindView(R.id.tablayout_toolbar) Toolbar toolbar;
    @BindView(R.id.tablayout_viewpager) ViewPager viewPager;
    @BindView(R.id.tablayout_tablayout) TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        ab.setDisplayHomeAsUpEnabled(true);

        if(navigationView != null){
            setupDrawerContent(navigationView);
        }
        if(viewPager!=null){
            setupViewPager(viewPager);
        }
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.bookmark:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.three:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.four:
                        viewPager.setCurrentItem(3);
                        break;
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
    private void setupViewPager(ViewPager viewPager){
        HomeUserVPAdapter adapter = new HomeUserVPAdapter(getSupportFragmentManager());
        adapter.addFragment(new DummyOneFragment(), "Newest");
        adapter.addFragment(new DummyTwoFragment(), "Popular");
        adapter.addFragment(new DummyThreeFragment(), "Event");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings){
            return true;
        }
        switch (id){
            case android.R.id.home:
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

