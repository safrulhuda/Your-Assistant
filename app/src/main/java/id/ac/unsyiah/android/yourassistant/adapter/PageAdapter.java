package id.ac.unsyiah.android.yourassistant.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import id.ac.unsyiah.android.yourassistant.fragment.JumatFragment;
import id.ac.unsyiah.android.yourassistant.fragment.KamisFragment;
import id.ac.unsyiah.android.yourassistant.fragment.RabuFragment;
import id.ac.unsyiah.android.yourassistant.fragment.SelasaFragment;
import id.ac.unsyiah.android.yourassistant.fragment.SeninFragment;

/**
 * Created by USER PC on 10/06/2018.
 */

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                SeninFragment tab1 = new SeninFragment();
                return tab1;
            case 1:
                SelasaFragment tab2 = new SelasaFragment();
                return tab2;
            case 2:
                RabuFragment tab3 = new RabuFragment();
                return tab3;
            case 3:
                KamisFragment tab4 = new KamisFragment();
                return tab4;
            case 4:
                JumatFragment tab5 = new JumatFragment();
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
