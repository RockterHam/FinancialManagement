package com.example.financialmanagement.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.financialmanagement.R;

import java.util.ArrayList;

import im.dacer.androidcharts.LineView;

public class StatisticsFragment extends Fragment {
    private CardView cvUserIcon;
    private ImageView ivUserIcon;
    private LinearLayout ltStatisticsPercent;


    ArrayList<String> xList = new ArrayList<>();
    ArrayList<Integer> expendList = new ArrayList<>();
    ArrayList<Integer> incomeList = new ArrayList<>();
    ArrayList<ArrayList<Integer>> dataList = new ArrayList<>();



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistics_fragment, container, false);

        for (int i = 1; i <= 12; i++){
            xList.add(i+"月");
        }
        dataList.add(expendList);
        dataList.add(incomeList);
        expendList.add(500);
        expendList.add(1000);
        incomeList.add(200);
        incomeList.add(300);

        ltStatisticsPercent = view.findViewById(R.id.lt_statistics_percent);
        LineView lineView = view.findViewById(R.id.line_view);
        lineView.setDrawDotLine(true); //optional
        lineView.setShowPopup(LineView.SHOW_POPUPS_MAXMIN_ONLY); //optional
        lineView.setBottomTextList(xList);
        lineView.setColorArray(new int[]{Color.rgb(254, 212, 54),Color.BLACK,Color.GRAY,Color.CYAN});
        lineView.setBackgroundColor(Color.WHITE);
        lineView.setDataList(dataList); //or lineView.setFloatDataList(floatDataLists)

        MyHorizontalStatisticsView myHorizontalStatisticsView = new MyHorizontalStatisticsView(getContext());


        return view;
    }
}


