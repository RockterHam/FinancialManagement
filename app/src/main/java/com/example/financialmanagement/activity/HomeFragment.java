package com.example.financialmanagement.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.example.financialmanagement.R;
import com.example.financialmanagement.dao.SQLiteOperation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private ListView listView;
    private ImageView ivNew;
    private TextView tvHomeExpend, tvHomeIncome, tvHomeYear, tvHomeMonth;
    private ArrayAdapter<String> adapter;
    private List<Map<String, Object>> list;
    private TimePickerView pvTime;
    private LinearLayout ltHomeTimeseletor;
    String dateStr = "";//现在列表中的表项的时间，精确到yy-mm-dd

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        listView = view.findViewById(R.id.lv_home);
        tvHomeExpend = view.findViewById(R.id.tv_home_expend);
        tvHomeIncome = view.findViewById(R.id.tv_home_income);
        tvHomeYear = view.findViewById(R.id.tv_home_year);
        tvHomeMonth = view.findViewById(R.id.tv_home_month);
        ltHomeTimeseletor = view.findViewById(R.id.lt_home_timeseletor);
        ivNew =  view.findViewById(R.id.iv_new);

        //List<Map<String, Object>> list = getData();
        //HomeAdapter adapter = new HomeAdapter(getActivity(), list);
        //通过setAdapter而把数据绑定到ListView中
        //listView.setAdapter(adapter);

        ivNew.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NewBill.class);
            getActivity().startActivity(intent);
        });

        listView.setOnItemClickListener((parent, viewList, position, id) -> {
            Map map = getData(dateStr).get(position);
            map.get("amount").toString();
            Intent intent = new Intent(getActivity(),BillAttributes.class);
            intent.putExtra("UUID",map.get("UUID").toString());
            intent.putExtra("category",map.get("category").toString());
            intent.putExtra("amount",map.get("amount").toString());
            intent.putExtra("date",map.get("date").toString());
            intent.putExtra("remark",map.get("remark").toString());
            startActivityForResult(intent,1);
        });

        //别人的轮子改改就行
////////////////////////////////////////////////////////////////////////////////////////////////////
        //点击激活选择器
        ltHomeTimeseletor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击组件的点击事件
                pvTime.show(ltHomeTimeseletor);
            }
        });

        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();//今天时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2010, 0, 01);//开始时间
        Calendar endDate = Calendar.getInstance();
        endDate.set(2029, 12, 31);//结束时间

        //时间选择器设置属性
        pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                dateStr = getTimes(date);//回调后得到的date放入dateStr存储，yy-mm-dd
                String[] temp = dateStr.split("-");
                tvHomeYear.setText(temp[0]);
                tvHomeMonth.setText(temp[1]);
                freshList();
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, false, false, false, false})
                .setLabel("年", "月", "日", "时", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.rgb(00,00,00))
                .setContentSize(25)
                .setCancelColor(Color.rgb(00,00,00))
                .setSubmitColor(Color.rgb(00,00,00))
                .setTitleBgColor(Color.rgb(248,201,43))
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .isCyclic(true)
                .setDecorView(null)
                .build();
////////////////////////////////////////////////////////////////////////////////////////////////////
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Date today = new Date();
        dateStr = getMonthTimes(today);
        String[] temp = dateStr.split("-");
        tvHomeYear.setText(temp[0]);
        tvHomeMonth.setText(temp[1]);
        freshList();
    }

    //重新获取数据并刷新列表
    private void freshList(){
        HomeAdapter adapter = new HomeAdapter(getActivity(), getData(dateStr));
        listView.setAdapter(adapter);
        calcAmount(getData(dateStr));
    }

    //根据表名、日期查询，获取列表
    public List<Map<String, Object>> getData(String date) {
        SQLiteOperation sqLiteOperation = new SQLiteOperation();
        return sqLiteOperation.query(getActivity(),"Info",date);
    }

    //计算统计周期内收支金额
    private void calcAmount(List<Map<String, Object>> list){
        double expend = 0;
        double income = 0;
        for (int i = 0; i < list.size(); i++){
            Map map = list.get(i);
            if (map.get("amount").toString().contains("-")){
                expend += Double.parseDouble(map.get("amount").toString().replace("-",""));
            }else {
                income += Double.parseDouble(map.get("amount").toString());
            }
        }
        tvHomeExpend.setText(String.valueOf(expend));
        tvHomeIncome.setText(String.valueOf(income));
    }

    //获取yy-mm-dd
    private String getTimes(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    //获取yy-mm
    private String getMonthTimes(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

}
