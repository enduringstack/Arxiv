package com.chen.arxiv.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.chen.arxiv.fragment.ArtificialIntelligenceFragment;
import com.chen.arxiv.fragment.ComputerVisionFragment;
import com.chen.arxiv.fragment.GraphicsFragment;
import com.chen.arxiv.fragment.HardwareArchitectureFragment;
import com.chen.arxiv.fragment.MathFragment;
import com.chen.arxiv.fragment.ComputationWithLanguageFragment;
import com.chen.arxiv.fragment.PhysicsFragment;
import com.chen.arxiv.fragment.SignalProcessingFragment;

import java.util.List;

/**
 * Created by chen on 18-2-27.
 */

public class FragmentsAdapter extends FragmentPagerAdapter{

    private List<String> list;

    public FragmentsAdapter(FragmentManager fm, List<String> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0){
            fragment =  new ComputerVisionFragment();
        }

        if (position ==1 ){
            fragment = new HardwareArchitectureFragment();
        }

        if (position == 2){
            fragment = new ComputerVisionFragment();
        }

        if (position == 3){
            fragment = new ComputationWithLanguageFragment();
        }

        if (position == 4){
            fragment = new ArtificialIntelligenceFragment();
        }

        if (position == 5){
            fragment = new GraphicsFragment();
        }

        if (position == 6){
            fragment = new SignalProcessingFragment();
        }

        if (position == 7){
            fragment = new MathFragment();
        }

        if (position == 8){
            fragment = new PhysicsFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
