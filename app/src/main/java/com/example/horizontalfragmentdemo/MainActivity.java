package com.example.horizontalfragmentdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private HorizontalScrollView hs;
    private LinearLayout lin_title;
    private ViewPager vp;
    private String[] titles;
    private List<TextView> textlist;
    private int lastPosition = 0;


    private void initView() {
        hs = findViewById(R.id.hs);
        lin_title = findViewById(R.id.lin_title);
        vp = findViewById(R.id.vp);
        titles = new String[]{"头条", "娱乐", "科技", "信息", "八卦", "北京", "上海", "天津", "重庆", "大大燕网"};

        //设置放置TextView的集合
        textlist = new ArrayList<TextView>();
        for (int i = 0; i < titles.length; i++) {
            TextView textView = new TextView(this);
            textView.setText(titles[i]);
            textView.setWidth(getWidth() / 5);
            textView.setGravity(Gravity.CENTER);
            // textView.setWidth(100);
            textView.setTextSize(20);
            if (i == 0) {
                textView.setTextColor(Color.RED);
            } else {
                textView.setTextColor(Color.BLACK);
            }
            // 处理标题文字的点击事件
            textView.setOnClickListener(this);
            // 定义id
            textView.setId(i + 10000);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
//            params.setMargins(20, 10, 20, 10);

            lin_title.addView(textView, params);
            // 往集合中添加textView
            textlist.add(textView);
        }
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return TitleFragment.getInstance(titles[position]);
            }

            @Override
            public int getCount() {
                return titles.length;
            }
        });
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < textlist.size(); i++) {
                    if (position == i) { //注意这个判断条件；
                        // 1      0123456
                        textlist.get(i).setTextColor(Color.RED);
                    } else {
                        textlist.get(i).setTextColor(Color.BLACK);
                    }
                }

                TextView textView = textlist.get(position);
                int width = textView.getWidth();
                // 计算该滑到什么位置
                Log.e("tag", "滑到了" + position);
                if (position > 4) {
                    hs.scrollTo(width + width * (position - 4), 0);
                } else {
//                    if (lastPosition < position) {
                    hs.scrollTo(-width * position, 0);
//                    } else {
//                        hs.scrollTo(width * position, 0);
//                    }
                }

                lastPosition = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        vp.setCurrentItem(id - 10000);//得到的是viewpagee的下标；

    }


    private int getWidth() {
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        int width3 = dm.widthPixels;
        int height3 = dm.heightPixels;
        return width3;
    }
}