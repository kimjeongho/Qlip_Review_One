package com.example.tenqube.qlip_review_one.viewpageranim;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;


import com.example.tenqube.qlip_review_one.R;
import com.example.tenqube.qlip_review_one.data.ViewPagerData;

import java.util.ArrayList;


/**
 * Created by tenqube on 2016-04-15.
 */
public class ViewPagerPresenterImpl implements ViewPagerPresenter{
    private ViewPagerModel mViewPagerModel;
    private ArrayList<ViewPagerData> viewPagerDataArrayList = new ArrayList<>();
//    private ViewPagerActivity_MVP_IndicatorState mActivity;
    private ViewPagerActivity mActivity;

    /*public ViewPagerPresenterImpl(Context context){
        this.mActivity = (ViewPagerActivity_MVP_IndicatorState) context;
        mViewPagerModel = new ViewPagerModel(mActivity);
    }*/

    public ViewPagerPresenterImpl(Context context){
        this.mActivity = (ViewPagerActivity) context;
        mViewPagerModel = new ViewPagerModel(mActivity);
    }

    @Override
    public ArrayList<ViewPagerData> loadViewPagerData() {
        viewPagerDataArrayList = mViewPagerModel.loadViewPagerList();
        return viewPagerDataArrayList;
    }

    @Override
    public void updateIndicators(int position, ImageView[] indicators) {

        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackgroundResource(
                    i == position ? R.drawable.indicator_selected : R.drawable.indicator_unselected
            );
        }
    }

    //color = 배경화면
    @Override
    public int updateColor(int position, float positionOffset, int positionOffsetPixels) {
        //background color 부분
        final int color1 = ContextCompat.getColor(mActivity, R.color.cyan);
        final int color2 = ContextCompat.getColor(mActivity, R.color.orange);
        final int color3 = ContextCompat.getColor(mActivity, R.color.green);

        final int[] colorList = new int[]{color1,color2,color3};

        final ArgbEvaluator evaluator = new ArgbEvaluator();    //page간 넘어갈때 색이 자연스럽게 넘어 가도록 설정하기 위해 사용

        int colorUpdate = (Integer) evaluator.evaluate(positionOffset, colorList[position]/*page변경전 값*/, colorList[position == mViewPagerModel.loadViewPagerList().size()-1 ? position : position + 1]/*변경될 page값*/);
        return colorUpdate;
    }
}
