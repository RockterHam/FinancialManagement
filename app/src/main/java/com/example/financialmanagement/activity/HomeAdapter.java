package com.example.financialmanagement.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.financialmanagement.R;

import java.util.List;
import java.util.Map;

public class HomeAdapter extends BaseAdapter {

    private List<Map<String , Object>> list;
    private LayoutInflater inflater;
    private Context context;

    public HomeAdapter(Context context, List<Map<String, Object>> list) {
        this.inflater = LayoutInflater.from(context);
        setList(list);
    }


    public void setList(List<Map<String , Object>> list){
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }//行数

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }//数据的对象

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.home_item,null);

        ImageView categoryIcon = (ImageView) view.findViewById(R.id.iv_home_category);
        TextView categoryText = (TextView) view.findViewById(R.id.tv_home_category);
        TextView amountText = (TextView) view.findViewById(R.id.tv_home_amount);

        Map map = list.get(position);

        categoryText.setText(map.get("category").toString());
        amountText.setText(map.get("amount").toString());

        setIcon(map, categoryIcon);

        return view;
    }

    private void setIcon(Map map,ImageView categoryIcon){
        //控制图标
        if (map.get("category").equals("餐饮")){
            categoryIcon.setImageResource(R.drawable.food_on);
        }
        if (map.get("category").equals("购物")){
            categoryIcon.setImageResource(R.drawable.shopping_on);
        }
        if (map.get("category").equals("日用")){
            categoryIcon.setImageResource(R.drawable.daily_on);
        }
        if (map.get("category").equals("交通")){
            categoryIcon.setImageResource(R.drawable.transfer_on);
        }
        if (map.get("category").equals("蔬菜")){
            categoryIcon.setImageResource(R.drawable.vegetables_on);
        }
        if (map.get("category").equals("水果")){
            categoryIcon.setImageResource(R.drawable.fruit_on);
        }
        if (map.get("category").equals("零食")){
            categoryIcon.setImageResource(R.drawable.snacks_on);
        }
        if (map.get("category").equals("运动")){
            categoryIcon.setImageResource(R.drawable.sport_on);
        }
        if (map.get("category").equals("工资")){
            categoryIcon.setImageResource(R.drawable.salary_on);
        }
        if (map.get("category").equals("兼职")){
            categoryIcon.setImageResource(R.drawable.wage_on);
        }
        if (map.get("category").equals("理财")){
            categoryIcon.setImageResource(R.drawable.stock_on);
        }
        if (map.get("category").equals("礼金")){
            categoryIcon.setImageResource(R.drawable.gift_on);
        }
        if (map.get("category").equals("其它")){
            categoryIcon.setImageResource(R.drawable.others_on);
        }
    }
}

