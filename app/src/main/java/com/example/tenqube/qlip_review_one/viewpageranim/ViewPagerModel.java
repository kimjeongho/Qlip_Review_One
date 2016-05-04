package com.example.tenqube.qlip_review_one.viewpageranim;

import android.content.Context;


import com.example.tenqube.qlip_review_one.R;
import com.example.tenqube.qlip_review_one.data.ViewPagerData;

import java.util.ArrayList;

/**
 * Created by tenqube on 2016-04-15.
 */
public class ViewPagerModel {
    private Context mContext;

    public ViewPagerModel(Context context){
        this.mContext = context;
    }

    public ArrayList<ViewPagerData> loadViewPagerList(){
        ViewPagerData viewPagerData;
        ArrayList<ViewPagerData> viewPagerList = new ArrayList<>();

        viewPagerData = new ViewPagerData();
        viewPagerData.FirstImg = R.drawable.img_onboarding1_first;
        viewPagerData.SecondImg = R.drawable.img_onboarding1_second;
        viewPagerData.TitleView = "Page 1";
        viewPagerData.ContentView = "content 1";
        viewPagerList.add(viewPagerData);

        viewPagerData = new ViewPagerData();
        viewPagerData.FirstImg = R.drawable.img_onboarding2_first;
        viewPagerData.SecondImg = R.drawable.img_onboarding2_second;
        viewPagerData.ThirdImg = R.drawable.img_onboarding2_third;
        viewPagerData.TitleView = "Page 2";
        viewPagerData.ContentView = "content 2";
        viewPagerList.add(viewPagerData);

        viewPagerData = new ViewPagerData();
        viewPagerData.FirstImg = R.drawable.img_onboarding3_first;
        viewPagerData.SecondImg = R.drawable.img_onboarding3_second;
        viewPagerData.TitleView = "Page 3";
        viewPagerData.ContentView = "content 3";
        viewPagerList.add(viewPagerData);

        return viewPagerList;
    }
}
