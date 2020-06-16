package com.example.financialmanagement;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BillFragment extends Fragment implements View.OnClickListener{
    private Button bt_expend_DEL , bt_expend_Date , bt_expend_1 , bt_expend_2 , bt_expend_3 ,
            bt_expend_4 , bt_expend_5 , bt_expend_6 , bt_expend_7 , bt_expend_8 , bt_expend_9 ,
            bt_expend_0 , bt_expend_Dot , bt_expend_Enter , bt_expend_BackSpace;
    private Button bt_income_DEL , bt_income_Date , bt_income_1 , bt_income_2 , bt_income_3 ,
            bt_income_4 , bt_income_5 , bt_income_6 , bt_income_7 , bt_income_8 , bt_income_9 ,
            bt_income_0 , bt_income_Dot , bt_income_Enter , bt_income_BackSpace;
    private TextView tvBillExpendAmount , tvBillIncomeAmount;
    private EditText etBillExpendRemark , etBillIncomeRemark;
    private TimePickerView pvTime;
    private ImageView ivBillFood , ivBillShopping , ivBillDaily , ivBillTransfer , ivBillVegetables ,
            ivBillFruit , ivBillSnacks , ivBillSport , ivBillSalary , ivBillWage , ivBillStock ,
            ivBillGift , ivBillOthers;
    private String expendCategory = "", incomeCategory = "";
    
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static BillFragment newInstance(int sectionNumber) {
        BillFragment fragment = new BillFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int num = getArguments().getInt(ARG_SECTION_NUMBER);
        Date date = new Date();
        if (num == 1) {
            View view = inflater.inflate(R.layout.expend_fragment, container, false);
            getExpendId(view);
            setExpendListener();
            bt_expend_Date.setText(getTimes(date));
            return view;
        } else if (num == 2) {
            View view = inflater.inflate(R.layout.income_fragment, container, false);
            getIncomeId(view);
            setIncomeListener();
            bt_income_Date.setText(getTimes(date));
            return view;
        }

        return null;
    }
    
    @Override
    public void onClick(View view) {
        int v = view.getId();
        switch(v) {
            case R.id.bt_expend_1:
                if (tvBillExpendAmount.getText().toString().equals("0")){
                    tvBillExpendAmount.setText("1");
                }else {
                    tvBillExpendAmount.append("1");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_2:
                if (tvBillExpendAmount.getText().toString().equals("0")){
                    tvBillExpendAmount.setText("2");
                }else {
                    tvBillExpendAmount.append("2");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_3:
                if (tvBillExpendAmount.getText().toString().equals("0")){
                    tvBillExpendAmount.setText("3");
                }else {
                    tvBillExpendAmount.append("3");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_4:
                if (tvBillExpendAmount.getText().toString().equals("0")){
                    tvBillExpendAmount.setText("4");
                }else {
                    tvBillExpendAmount.append("4");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_5:
                if (tvBillExpendAmount.getText().toString().equals("0")){
                    tvBillExpendAmount.setText("5");
                }else {
                    tvBillExpendAmount.append("5");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_6:
                if (tvBillExpendAmount.getText().toString().equals("0")){
                    tvBillExpendAmount.setText("6");
                }else {
                    tvBillExpendAmount.append("6");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_7:
                if (tvBillExpendAmount.getText().toString().equals("0")){
                    tvBillExpendAmount.setText("7");
                }else {
                    tvBillExpendAmount.append("7");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_8:
                if (tvBillExpendAmount.getText().toString().equals("0")){
                    tvBillExpendAmount.setText("8");
                }else {
                    tvBillExpendAmount.append("8");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_9:
                if (tvBillExpendAmount.getText().toString().equals("0")){
                    tvBillExpendAmount.setText("9");
                }else {
                    tvBillExpendAmount.append("9");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_0:
                if (tvBillExpendAmount.getText().toString().equals("0")){
                    //tvBillExpendAmount.setText("1");
                }else {
                    tvBillExpendAmount.append("0");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_Dot:
                String tempEDot = tvBillExpendAmount.getText().toString();
                if (!tempEDot.contains(".")){
                    tvBillExpendAmount.append(".");
                }else {
                    Toast.makeText(getContext(),"存在小数点", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_expend_Delete:
                tvBillExpendAmount.setText("0");
                break;
                ////////////////////////////////////
            case R.id.bt_expend_Date:
                timeSelector();
                pvTime.show(bt_expend_Date);
                break;
                ////////////////////////////////////
            case R.id.bt_expend_Backspace:
                String tempEBackspace = tvBillExpendAmount.getText().toString();
                if (tempEBackspace.equals("0")){
                    Toast.makeText(getContext(),"已经清空", Toast.LENGTH_SHORT).show();
                }else {
                    tempEBackspace = tempEBackspace.substring(0,tempEBackspace.length()- 1);
                    if (tempEBackspace.isEmpty()){
                        tvBillExpendAmount.setText("0");
                    }else {
                        tvBillExpendAmount.setText(tempEBackspace);
                    }
                }
                break;
            case R.id.bt_expend_Enter:
                String tempEEnter = tvBillExpendAmount.getText().toString();
                if (tempEEnter.equals("0")){
                    Toast.makeText(getContext(),"请输入金额", Toast.LENGTH_SHORT).show();
                }else if (expendCategory.isEmpty()){
                    Toast.makeText(getContext(),"请选择类别", Toast.LENGTH_SHORT).show();
                }else {
                    expendEnter();
                    getActivity().finish();
                }
                break;
            case R.id.iv_bill_food:
                clearExpendSelect();
                ivBillFood.setImageResource(R.drawable.food_on);
                expendCategory = "餐饮";
                break;
            case R.id.iv_bill_shopping:
                clearExpendSelect();
                ivBillShopping.setImageResource(R.drawable.shopping_on);
                expendCategory = "购物";
                break;
            case R.id.iv_bill_daily:
                clearExpendSelect();
                ivBillDaily.setImageResource(R.drawable.daily_on);
                expendCategory = "日用";
                break;
            case R.id.iv_bill_transfer:
                clearExpendSelect();
                ivBillTransfer.setImageResource(R.drawable.transfer_on);
                expendCategory = "交通";
                break;
            case R.id.iv_bill_vegetables:
                clearExpendSelect();
                ivBillVegetables.setImageResource(R.drawable.vegetables_on);
                expendCategory = "蔬菜";
                break;
            case R.id.iv_bill_fruit:
                clearExpendSelect();
                ivBillFruit.setImageResource(R.drawable.fruit_on);
                expendCategory = "水果";
                break;
            case R.id.iv_bill_snacks:
                clearExpendSelect();
                ivBillSnacks.setImageResource(R.drawable.snacks_on);
                expendCategory = "零食";
                break;
            case R.id.iv_bill_sport:
                clearExpendSelect();
                ivBillSport.setImageResource(R.drawable.sport_on);
                expendCategory = "运动";
                break;
////////////////////////////////////////////////////////////////////////////////////////////////////
            case R.id.bt_income_1:
                if (tvBillIncomeAmount.getText().toString().equals("0")){
                    tvBillIncomeAmount.setText("1");
                }else {
                    tvBillIncomeAmount.append("1");
                    isOverFlow(tvBillIncomeAmount);
                }
                break;
            case R.id.bt_income_2:
                if (tvBillIncomeAmount.getText().toString().equals("0")){
                    tvBillIncomeAmount.setText("2");
                }else {
                    tvBillIncomeAmount.append("2");
                    isOverFlow(tvBillIncomeAmount);
                }
                break;
            case R.id.bt_income_3:
                if (tvBillIncomeAmount.getText().toString().equals("0")){
                    tvBillIncomeAmount.setText("3");
                }else {
                    tvBillIncomeAmount.append("3");
                    isOverFlow(tvBillIncomeAmount);
                }
                break;
            case R.id.bt_income_4:
                if (tvBillIncomeAmount.getText().toString().equals("0")){
                    tvBillIncomeAmount.setText("4");
                }else {
                    tvBillIncomeAmount.append("4");
                    isOverFlow(tvBillIncomeAmount);
                }
                break;
            case R.id.bt_income_5:
                if (tvBillIncomeAmount.getText().toString().equals("0")){
                    tvBillIncomeAmount.setText("5");
                }else {
                    tvBillIncomeAmount.append("5");
                    isOverFlow(tvBillIncomeAmount);
                }
                break;
            case R.id.bt_income_6:
                if (tvBillIncomeAmount.getText().toString().equals("0")){
                    tvBillIncomeAmount.setText("6");
                }else {
                    tvBillIncomeAmount.append("6");
                    isOverFlow(tvBillIncomeAmount);
                }
                break;
            case R.id.bt_income_7:
                if (tvBillIncomeAmount.getText().toString().equals("0")){
                    tvBillIncomeAmount.setText("7");
                }else {
                    tvBillIncomeAmount.append("7");
                    isOverFlow(tvBillIncomeAmount);
                }
                break;
            case R.id.bt_income_8:
                if (tvBillIncomeAmount.getText().toString().equals("0")){
                    tvBillIncomeAmount.setText("8");
                }else {
                    tvBillIncomeAmount.append("8");
                    isOverFlow(tvBillIncomeAmount);
                }
                break;
            case R.id.bt_income_9:
                if (tvBillIncomeAmount.getText().toString().equals("0")){
                    tvBillIncomeAmount.setText("9");
                }else {
                    tvBillIncomeAmount.append("9");
                    isOverFlow(tvBillIncomeAmount);
                }
                break;
            case R.id.bt_income_0:
                if (tvBillIncomeAmount.getText().toString().equals("0")){
                    //tvBillIncomeAmount.setText("1");
                }else {
                    tvBillIncomeAmount.append("0");
                    isOverFlow(tvBillIncomeAmount);
                }
                break;
            case R.id.bt_income_Dot:
                String tempIDot = tvBillIncomeAmount.getText().toString();
                if (!tempIDot.contains(".")){
                    tvBillIncomeAmount.append(".");
                }else {
                    Toast.makeText(getContext(),"存在小数点", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_income_Delete:
                tvBillIncomeAmount.setText("0");
                break;
            ////////////////////////////////////
            case R.id.bt_income_Date:
                timeSelector();
                pvTime.show(bt_income_Date);
                break;
            ////////////////////////////////////
            case R.id.bt_income_Backspace:
                String tempIBackspace = tvBillIncomeAmount.getText().toString();
                if (tempIBackspace.equals("0")){
                    Toast.makeText(getContext(),"已经清空", Toast.LENGTH_SHORT).show();
                }else {
                    tempIBackspace = tempIBackspace.substring(0,tempIBackspace.length()- 1);
                    if (tempIBackspace.isEmpty()){
                        tvBillIncomeAmount.setText("0");
                    }else {
                        tvBillIncomeAmount.setText(tempIBackspace);
                    }
                }
                break;
            case R.id.bt_income_Enter:
                String tempIEnter = tvBillIncomeAmount.getText().toString();
                if (tempIEnter.equals("0")){
                    Toast.makeText(getContext(),"请输入金额", Toast.LENGTH_SHORT).show();
                } else if (incomeCategory.isEmpty()){
                    Toast.makeText(getContext(),"请选择类别", Toast.LENGTH_SHORT).show();
                } else {
                    incomeEnter();
                    getActivity().finish();
                }
                break;
            case R.id.iv_bill_salary:
                clearIncomeSelect();
                ivBillSalary.setImageResource(R.drawable.salary_on);
                incomeCategory = "工资";
                break;
            case R.id.iv_bill_wage:
                clearIncomeSelect();
                ivBillWage.setImageResource(R.drawable.wage_on);
                incomeCategory = "兼职";
                break;
            case R.id.iv_bill_stock:
                clearIncomeSelect();
                ivBillStock.setImageResource(R.drawable.stock_on);
                incomeCategory = "理财";
                break;
            case R.id.iv_bill_gift:
                clearIncomeSelect();
                ivBillGift.setImageResource(R.drawable.gift_on);
                incomeCategory = "礼金";
                break;
            case R.id.iv_bill_others:
                clearIncomeSelect();
                ivBillOthers.setImageResource(R.drawable.others_on);
                incomeCategory = "其它";
                break;
        }
    }

    private void isOverFlow(TextView textView){
        if (textView.getText().toString().length() == 8){
            Toast.makeText(getContext(), "别买了别买了", Toast.LENGTH_LONG).show();
        }
    }

    //时间选择器
    private void timeSelector(){

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
                bt_expend_Date.setText(getTimes(date));//回调后得到的date放入dateStr存储，yy-mm-dd
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
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
    }

    //清除Expend选中的图标
    private void clearExpendSelect(){
        ivBillFood.setImageResource(R.drawable.food_off);
        ivBillShopping.setImageResource(R.drawable.shopping_off);
        ivBillDaily.setImageResource(R.drawable.daily_off);
        ivBillTransfer.setImageResource(R.drawable.transfer_off);
        ivBillVegetables.setImageResource(R.drawable.vegetables_off);
        ivBillFruit.setImageResource(R.drawable.fruit_off);
        ivBillSnacks.setImageResource(R.drawable.snacks_off);
        ivBillSport.setImageResource(R.drawable.sport_off);
    }

    private void clearIncomeSelect(){
        ivBillSalary.setImageResource(R.drawable.salary_off);
        ivBillWage.setImageResource(R.drawable.wage_off);
        ivBillStock.setImageResource(R.drawable.stock_off);
        ivBillGift.setImageResource(R.drawable.gift_off);
        ivBillOthers.setImageResource(R.drawable.others_off);
    }
    //向数据库写入expend
    private void expendEnter() {
        SQLiteOperation sqLiteOperation = new SQLiteOperation();
        sqLiteOperation.add(getActivity(),expendCategory, "-"+tvBillExpendAmount.getText().toString(),
                bt_expend_Date.getText().toString(), etBillExpendRemark.getText().toString());
    }
    //向数据库写入income
    private void incomeEnter() {
        SQLiteOperation sqLiteOperation = new SQLiteOperation();
        sqLiteOperation.add(getActivity(),incomeCategory, tvBillIncomeAmount.getText().toString(),
                bt_income_Date.getText().toString(), etBillIncomeRemark.getText().toString());
    }

    //获取yy-mm-dd
    private String getTimes(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private void getExpendId(View view){
        tvBillExpendAmount = view.findViewById(R.id.tv_bill_expend_amount);
        etBillExpendRemark = view.findViewById(R.id.et_expend_remark);
        bt_expend_1 = view.findViewById(R.id.bt_expend_1);
        bt_expend_2 = view.findViewById(R.id.bt_expend_2);
        bt_expend_3 = view.findViewById(R.id.bt_expend_3);
        bt_expend_4 = view.findViewById(R.id.bt_expend_4);
        bt_expend_5 = view.findViewById(R.id.bt_expend_5);
        bt_expend_6 = view.findViewById(R.id.bt_expend_6);
        bt_expend_7 = view.findViewById(R.id.bt_expend_7);
        bt_expend_8 = view.findViewById(R.id.bt_expend_8);
        bt_expend_9 = view.findViewById(R.id.bt_expend_9);
        bt_expend_0 = view.findViewById(R.id.bt_expend_0);
        bt_expend_DEL = view.findViewById(R.id.bt_expend_Delete);
        bt_expend_Date = view.findViewById(R.id.bt_expend_Date);
        bt_expend_Dot = view.findViewById(R.id.bt_expend_Dot);
        bt_expend_Enter = view.findViewById(R.id.bt_expend_Enter);
        bt_expend_BackSpace = view.findViewById(R.id.bt_expend_Backspace);
        ivBillFood = view.findViewById(R.id.iv_bill_food);
        ivBillShopping = view.findViewById(R.id.iv_bill_shopping);
        ivBillDaily = view.findViewById(R.id.iv_bill_daily);
        ivBillTransfer = view.findViewById(R.id.iv_bill_transfer);
        ivBillVegetables = view.findViewById(R.id.iv_bill_vegetables);
        ivBillFruit = view.findViewById(R.id.iv_bill_fruit);
        ivBillSnacks = view.findViewById(R.id.iv_bill_snacks);
        ivBillSport = view.findViewById(R.id.iv_bill_sport);

    }

    private void getIncomeId(View view){
        tvBillIncomeAmount = view.findViewById(R.id.tv_bill_income_amount);
        etBillIncomeRemark = view.findViewById(R.id.et_income_remark);
        bt_income_1 = view.findViewById(R.id.bt_income_1);
        bt_income_2 = view.findViewById(R.id.bt_income_2);
        bt_income_3 = view.findViewById(R.id.bt_income_3);
        bt_income_4 = view.findViewById(R.id.bt_income_4);
        bt_income_5 = view.findViewById(R.id.bt_income_5);
        bt_income_6 = view.findViewById(R.id.bt_income_6);
        bt_income_7 = view.findViewById(R.id.bt_income_7);
        bt_income_8 = view.findViewById(R.id.bt_income_8);
        bt_income_9 = view.findViewById(R.id.bt_income_9);
        bt_income_0 = view.findViewById(R.id.bt_income_0);
        bt_income_DEL = view.findViewById(R.id.bt_income_Delete);
        bt_income_Date = view.findViewById(R.id.bt_income_Date);
        bt_income_Dot = view.findViewById(R.id.bt_income_Dot);
        bt_income_Enter = view.findViewById(R.id.bt_income_Enter);
        bt_income_BackSpace = view.findViewById(R.id.bt_income_Backspace);
        ivBillSalary = view.findViewById(R.id.iv_bill_salary);
        ivBillWage = view.findViewById(R.id.iv_bill_wage);
        ivBillStock = view.findViewById(R.id.iv_bill_stock);
        ivBillGift = view.findViewById(R.id.iv_bill_gift);
        ivBillOthers = view.findViewById(R.id.iv_bill_others);
    }
    
    private void setExpendListener(){
        bt_expend_1.setOnClickListener(this);
        bt_expend_2.setOnClickListener(this);
        bt_expend_3.setOnClickListener(this);
        bt_expend_4.setOnClickListener(this);
        bt_expend_5.setOnClickListener(this);
        bt_expend_6.setOnClickListener(this);
        bt_expend_7.setOnClickListener(this);
        bt_expend_8.setOnClickListener(this);
        bt_expend_9.setOnClickListener(this);
        bt_expend_0.setOnClickListener(this);
        bt_expend_DEL.setOnClickListener(this);
        bt_expend_Date.setOnClickListener(this);
        bt_expend_Dot.setOnClickListener(this);
        bt_expend_Enter.setOnClickListener(this);
        bt_expend_BackSpace.setOnClickListener(this);
        ivBillFood.setOnClickListener(this);
        ivBillShopping.setOnClickListener(this);
        ivBillDaily.setOnClickListener(this);
        ivBillTransfer.setOnClickListener(this);
        ivBillVegetables.setOnClickListener(this);
        ivBillFruit.setOnClickListener(this);
        ivBillSnacks.setOnClickListener(this);
        ivBillSport.setOnClickListener(this);
    }

    private void setIncomeListener(){
        bt_income_1.setOnClickListener(this);
        bt_income_2.setOnClickListener(this);
        bt_income_3.setOnClickListener(this);
        bt_income_4.setOnClickListener(this);
        bt_income_5.setOnClickListener(this);
        bt_income_6.setOnClickListener(this);
        bt_income_7.setOnClickListener(this);
        bt_income_8.setOnClickListener(this);
        bt_income_9.setOnClickListener(this);
        bt_income_0.setOnClickListener(this);
        bt_income_DEL.setOnClickListener(this);
        bt_income_Date.setOnClickListener(this);
        bt_income_Dot.setOnClickListener(this);
        bt_income_Enter.setOnClickListener(this);
        bt_income_BackSpace.setOnClickListener(this);
        ivBillSalary.setOnClickListener(this);
        ivBillWage.setOnClickListener(this);
        ivBillStock.setOnClickListener(this);
        ivBillGift.setOnClickListener(this);
        ivBillOthers.setOnClickListener(this);
    }
}