package com.example.tenqube.qlip_review_one.viewpageranim;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tenqube.qlip_review_one.R;
import com.example.tenqube.qlip_review_one.data.ViewPagerData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tenqube on 2016-05-04.
 */
public class ViewPagerActivity extends AppCompatActivity implements View.OnClickListener {

    SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager viewPager;

    ImageView indi1, indi2, indi3;
    ImageView[] indicators;

    int page = 0;

    ViewPagerPresenter viewPagerPresenter;

    ImageButton mNextBtn;
    Button mSkipBtn;
    Button mFinishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //단말기 버전이 롤리팝(5.0)보다 크거나 같을때 처리 코드
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black_trans80));
        }   // 5.0버전이상일 경우 statusbar를 설정할수 있음.

        setContentView(R.layout.activity_pager);

        viewPagerPresenter = new ViewPagerPresenterImpl(ViewPagerActivity.this); // ViewPagerPresenterImpl객체 생성

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        //Adapter생성자에 AppCompatActivity안에 있는 getSupportFragmentManager을 보낸다. Pager을 사용하는 쪽에서는 생성자에 FragmentManager객체를 넘겨주어 생성한다.

        mNextBtn = (ImageButton) findViewById(R.id.intro_btn_next);    //다음 (>) 버튼
        mSkipBtn = (Button) findViewById(R.id.intro_btn_skip);          //스킵(skip)버튼
        mFinishBtn = (Button) findViewById(R.id.intro_btn_finish);      //종료(finish)버튼

        indi1 = (ImageView) findViewById(R.id.intro_indicator_0);
        indi2 = (ImageView) findViewById(R.id.intro_indicator_1);
        indi3 = (ImageView) findViewById(R.id.intro_indicator_2);

        indicators = new ImageView[]{
                indi1, indi2, indi3
        };  //indicators 생성

        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(mSectionsPagerAdapter);

        viewPager.setCurrentItem(page); // viewpager slide시 page넘어감
        viewPagerPresenter.updateIndicators(page, indicators);

        //Page변형 설정(Animation 효과 설정)
        viewPager.setPageTransformer(false, new IntroPageTransformer());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position/*선택한(slide) position*/, float positionOffset, int positionOffsetPixels) {
                int updateColor = viewPagerPresenter.updateColor(position, positionOffset, positionOffsetPixels);
                viewPager.setBackgroundColor(updateColor);
                //page가 이동하는 순간 색이 자연스럽게 바뀌기 위해 사용함.
            }

            @Override
            public void onPageSelected(int position) {  //선택한 page
                page = position;    // 해당 포지션을 page로 넘김
                final int ITEM_TOTAL = viewPagerPresenter.loadViewPagerData().size() - 1;

                viewPagerPresenter.updateIndicators(page, indicators);

                if (position == ITEM_TOTAL) {
                    mNextBtn.setVisibility(View.GONE);
                    mFinishBtn.setVisibility(View.VISIBLE);
                } else {
                    mNextBtn.setVisibility(View.VISIBLE);
                    mFinishBtn.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mSectionsPagerAdapter.addAll(viewPagerPresenter.loadViewPagerData());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.intro_btn_next:
                page += 1;
                viewPager.setCurrentItem(page, true); // (>) 누를시 viewpager 넘어감
                break;
            case R.id.intro_btn_skip:
                finish();
                break;
            case R.id.intro_btn_finish:
                finish();
                break;
        }
    }

    //Fragment--------------------------------------------------------------------------------------
    public static class PagerFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String PAGE = "page";

        private ViewPagerData data;
        private int mPage;

        public PagerFragment() {

        }

        public static PagerFragment newInstance(ViewPagerData position, int page) {
            //Activity는 인텐트로 값을 주고 받고 fragment는 argument로 값을 주고 받는다.
            //fragment생성할때마다 Bundle을 만들어 Argument를 전달 받고 전달하는것이 번거 롭기 때문에 메소드로 만든다.
            PagerFragment fragment = new PagerFragment();
            Bundle args = new Bundle();
            args.putSerializable(ARG_SECTION_NUMBER, position);
            args.putInt(PAGE,page);
            //Fragment에 값을 전달하기 위해서는 Bundle을 이용한다.
            //putSerializable : data의 object(전체 데이터)를 보낸다.

            fragment.setArguments(args);
            //전달할 값을 저장한 Bundle을 Fragment의 setArguments()로 Fragment에 설정한다.
            return fragment;

        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            data = (ViewPagerData) getArguments().getSerializable(ARG_SECTION_NUMBER);   //position을 얻어옴
            mPage = getArguments().getInt(PAGE);
            //Fragment는 getArguments로 전달한 Bundle을 얻어 올 수 있고, Bundle에서 저장한 값을 읽어 올수 있다.
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            int layoutResId;
            switch (mPage) {
                case 0:
                    layoutResId = R.layout.fragment_pager;
                    break;
                case 1:
                    layoutResId = R.layout.fragment_pager2;
                    break;
                default:
                    layoutResId = R.layout.fragment_pager3;

            }

//            View rootView = inflater.inflate(R.layout.fragment_pager, container,false);
            View rootView = inflater.inflate(layoutResId, container, false);
            switch (mPage) {
                case 0:
                    TextView labelView = (TextView) rootView.findViewById(R.id.section_label);
                    TextView contentView = (TextView) rootView.findViewById(R.id.content_text);
                    ImageView firstImage = (ImageView) rootView.findViewById(R.id.first_img);
                    ImageView secondImage = (ImageView) rootView.findViewById(R.id.second_img);
                    labelView.setText(data.TitleView);
                    contentView.setText(data.ContentView);
                    firstImage.setBackgroundResource(data.FirstImg);
                    secondImage.setBackgroundResource(data.SecondImg);
                    break;
                case 1:
                    labelView = (TextView) rootView.findViewById(R.id.section_label);
                    contentView = (TextView) rootView.findViewById(R.id.content_text);
                    firstImage = (ImageView) rootView.findViewById(R.id.first_img);
                    secondImage = (ImageView) rootView.findViewById(R.id.second_img);
                    ImageView thirdImage = (ImageView) rootView.findViewById(R.id.third_img);
                    labelView.setText(data.TitleView);
                    contentView.setText(data.ContentView);
                    firstImage.setBackgroundResource(data.FirstImg);
                    secondImage.setBackgroundResource(data.SecondImg);
                    thirdImage.setBackgroundResource(data.ThirdImg);
                    break;
                default:
                    labelView = (TextView) rootView.findViewById(R.id.section_label);
                    contentView = (TextView) rootView.findViewById(R.id.content_text);
                    firstImage = (ImageView) rootView.findViewById(R.id.first_img);
                    secondImage = (ImageView) rootView.findViewById(R.id.second_img);
                    labelView.setText(data.TitleView);
                    contentView.setText(data.ContentView);
                    firstImage.setBackgroundResource(data.FirstImg);
                    secondImage.setBackgroundResource(data.SecondImg);
                    break;
            }


            rootView.setTag(mPage);

            return rootView;

        }
    }

    //Fragment--------------------------------------------------------------------------------------
    //Adapter---------------------------------------------------------------------------------------
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
        List<ViewPagerData> items = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void add(ViewPagerData item) {
            items.add(item);
            notifyDataSetChanged();
        }

        public void addAll(ArrayList<ViewPagerData> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            return PagerFragment.newInstance(items.get(position), position); //newInstance 설정 부분
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }

    public class IntroPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {

            //Tag에서 현재 페이지 인덱스를 가져온다.(성능향상 위해)
            int pagePosition = (int) page.getTag();

            // 페이지의 너비를 가져온다. 페이지 계산 위해 !?
            int pageWidth = page.getWidth();
            float pageWidthTimesPosition = pageWidth * position;
            float absPosition = Math.abs(position);

            // 효과 주는 방법 (animation)
            if (position <= -1.0f || position >= 1.0f) {
                //페이지가 보이지 않는 공간 실행중인 작업 및 애니메이션 중지 => page Finish


            } else if (position == 0.0f) {
                //Page 선택 되어 있을때 => 애니메이션 재설정 가능

            } else {

                // 페이지가 슬라이드 되고 있는 상태 => 애니메이션 설정

                //TitleTextView 설정
                View title = page.findViewById(R.id.section_label);
                title.setTranslationY(-pageWidthTimesPosition / 2f);
                title.setAlpha(1.0f - absPosition);

                //contentTextView animation 설정 천천히 화면 밖으로 사라지게함
                View description = page.findViewById(R.id.content_text);
                description.setTranslationY(-pageWidthTimesPosition / 2f);
                description.setAlpha(1.0f - absPosition);

                //이미지 설정
                View firstImage = page.findViewById(R.id.first_img);
                View secondImage = page.findViewById(R.id.second_img);
                View thirdImage = page.findViewById(R.id.third_img);

                // ViewPager효과(Animation)를 만들시 이미지 뷰의 Null Check 필수!
                if (pagePosition == 0 && firstImage != null) {
                    firstImage.bringToFront();
                    firstImage.setAlpha(1.0f - absPosition);
                    firstImage.setTranslationX(pageWidthTimesPosition * 0.3f);

                    secondImage.setAlpha(1.0f - absPosition);
                    secondImage.setTranslationY(pageWidthTimesPosition * 1.0f);


                }

                if (pagePosition == 1 && firstImage != null) {
                    firstImage.setAlpha(1.0f - absPosition);


                    secondImage.setAlpha(1.0f - absPosition);
                    secondImage.setTranslationX(pageWidthTimesPosition * 0.3f);
                    secondImage.setTranslationY(pageWidthTimesPosition * 0.5f);

                    thirdImage.setAlpha(1.0f - absPosition);
                    thirdImage.setTranslationX(pageWidthTimesPosition * 0.3f);
                    thirdImage.setTranslationY(-pageWidthTimesPosition * 0.5f);

                }

                if (pagePosition == 2 && firstImage != null) {
                    //bringToFront : 이미지 우선 순위 지정
                    firstImage.bringToFront();

                    firstImage.setAlpha(1.0f - absPosition);
                    firstImage.setTranslationY(-pageWidthTimesPosition * 1.0f);

                    secondImage.setAlpha(1.0f - absPosition);

                }

                // Finally, it can be useful to know the direction
                // of the user's swipe - if we're entering or exiting.
                // This is quite simple:    //??
                if (position < 0) {
                    // Create your out animation here
                } else {
                    // Create your in animation here
                }
            }
        }

    }
}