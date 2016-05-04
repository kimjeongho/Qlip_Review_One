package com.example.tenqube.qlip_review_one.viewpageranim;

import android.widget.ImageView;

import com.example.tenqube.qlip_review_one.data.ViewPagerData;

import java.util.ArrayList;


/**
 * Created by tenqube on 2016-04-15.
 */
public interface ViewPagerPresenter {
    ArrayList<ViewPagerData> loadViewPagerData();   //등록되있는 item list...
    void updateIndicators(int position, ImageView[] indicators);    //indicator 설정
    int updateColor(int position/*선택한(slide) position*/, float positionOffset, int positionOffsetPixels);   //배경화면 update
}
