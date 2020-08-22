package com.example.horizontalfragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Author:  memoThree
 * Date  :  2020/8/22
 * Desc  :  .
 */
public class TitleFragment extends Fragment {


    public static TitleFragment getInstance(String title) {
        TitleFragment fragment1 = new TitleFragment();
        //创建bundle
        Bundle bundle = new Bundle();
        //添加值
        bundle.putString("title", title);
        //把值添加到Arguments中
        fragment1.setArguments(bundle);
        return fragment1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取Arguments
        Bundle arguments = getArguments();
        //获取值
        String title = arguments.getString("title");
        //创建textview
        TextView textView = new TextView(getActivity());
        //设置值
        textView.setText(title);
        return textView;
    }

}
