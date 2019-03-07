package id.ac.unsyiah.android.yourassistant;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import id.ac.unsyiah.android.yourassistant.adapter.PageAdapter;

public class MainActivity extends AppCompatActivity {

    TabLayout tablayout;
    TabItem tabSenin, tabSelasa, tabRabu, tabKamis, tabJumat;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.setIcon(R.drawable.logo_cover);

        tablayout = findViewById(R.id.tablayout);
        tabSenin = findViewById(R.id.tabSenin);
        tabSelasa = findViewById(R.id.tabSelasa);
        tabRabu = findViewById(R.id.tabRabu);
        tabKamis = findViewById(R.id.tabKamis);
        tabJumat = findViewById(R.id.tabJumat);
        viewPager = findViewById(R.id.viewPager);

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tablayout.getTabCount());

        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tambahJadwal:
                startActivity(new Intent(MainActivity.this,AddSchedule.class));
                return true;
        }
        return false;
    }
}
