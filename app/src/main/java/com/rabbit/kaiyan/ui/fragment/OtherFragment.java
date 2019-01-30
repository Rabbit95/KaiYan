package com.rabbit.kaiyan.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rabbit.kaiyan.R;

public class OtherFragment extends Fragment {
    private String mTitle;

    public static OtherFragment getInstance(String mTitle){
        OtherFragment fo = new OtherFragment();
        fo.mTitle = mTitle;
        return fo;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment1,null);
        TextView textView = v.findViewById(R.id.ft_1_tv);
        textView.setText(mTitle);
        return v;
    }
}
