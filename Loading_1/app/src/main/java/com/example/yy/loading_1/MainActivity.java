package com.example.yy.loading_1;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewPager();
        setTabLayout();
        setListener();

    }

    public void setTabLayout(){
        tabLayout = findViewById(R.id.tabLayout);
    }

    public void setViewPager(){
        viewPager = findViewById(R.id.viewPager);
        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
    public void setListener(){
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }



    public void onSearchClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), SearchListActivity.class);
        startActivity(intent);
    }
}

