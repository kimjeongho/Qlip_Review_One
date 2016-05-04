package com.example.tenqube.qlip_review_one;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tenqube.qlip_review_one.base.BaseActivity;
import com.example.tenqube.qlip_review_one.viewpageranim.ViewPagerActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        findViewById(R.id.btn_tranning).setOnClickListener(this);
        findViewById(R.id.btn_pager).setOnClickListener(this);
        findViewById(R.id.btn_chart).setOnClickListener(this);
        findViewById(R.id.btn_excel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_pager:
                intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
