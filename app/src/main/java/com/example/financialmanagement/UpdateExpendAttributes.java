package com.example.financialmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateExpendAttributes extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout ltExpend;
    private Button bt_expend_DEL , bt_expend_Date , bt_expend_1 , bt_expend_2 , bt_expend_3 ,
            bt_expend_4 , bt_expend_5 , bt_expend_6 , bt_expend_7 , bt_expend_8 , bt_expend_9 ,
            bt_expend_0 , bt_expend_Dot , bt_expend_Enter , bt_expend_BackSpace;
    private TextView tvBillExpendAmount;
    private EditText etBillExpendRemark;
    private TimePickerView pvTime;
    private ImageView ivBillFood , ivBillShopping , ivBillDaily , ivBillTransfer , ivBillVegetables ,
            ivBillFruit , ivBillSnacks , ivBillSport;
    private String expendCategory = "";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.expend_fragment);
        intent = getIntent();
        Date date = new Date();
        ltExpend = findViewById(R.id.lt_expend);
        addLinearLayout();

        getExpendId();
        setExpendListener();
        bt_expend_Date.setText(getTimes(date));

        setCategoryIcon();
        tvBillExpendAmount.setText(intent.getStringExtra("amount").replace("-",""));

        bt_expend_Date.setText(intent.getStringExtra("date"));
        etBillExpendRemark.setText(intent.getStringExtra("remark"));

    }

    //向数据库写入expend
    private void expendEnter() {
        SQLiteOperation sqLiteOperation = new SQLiteOperation();
        if (!(tvBillExpendAmount.getText().equals(intent.getStringExtra("amount").replace("-","")))){
            sqLiteOperation.update(this,intent.getStringExtra("UUID"),"amount","-"+tvBillExpendAmount.getText().toString());
        }
        if (!(bt_expend_Date.getText().equals(intent.getStringExtra("date")))){
            sqLiteOperation.update(this,intent.getStringExtra("UUID"),"date",bt_expend_Date.getText().toString());
        }
        if (!(expendCategory.equals(intent.getStringExtra("category")))){
            sqLiteOperation.update(this,intent.getStringExtra("UUID"),"category",expendCategory);
        }
        if (!(etBillExpendRemark.getText().equals(intent.getStringExtra("remark")))){
            sqLiteOperation.update(this,intent.getStringExtra("UUID"),"remark",etBillExpendRemark.getText().toString());
        }

    }


    @Override
    public void onClick(View view) {
        int v = view.getId();
        switch(v) {
            case R.id.bt_expend_1:
                if (tvBillExpendAmount.getText().toString().equals("0")) {
                    tvBillExpendAmount.setText("1");
                } else {
                    tvBillExpendAmount.append("1");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_2:
                if (tvBillExpendAmount.getText().toString().equals("0")) {
                    tvBillExpendAmount.setText("2");
                } else {
                    tvBillExpendAmount.append("2");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_3:
                if (tvBillExpendAmount.getText().toString().equals("0")) {
                    tvBillExpendAmount.setText("3");
                } else {
                    tvBillExpendAmount.append("3");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_4:
                if (tvBillExpendAmount.getText().toString().equals("0")) {
                    tvBillExpendAmount.setText("4");
                } else {
                    tvBillExpendAmount.append("4");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_5:
                if (tvBillExpendAmount.getText().toString().equals("0")) {
                    tvBillExpendAmount.setText("5");
                } else {
                    tvBillExpendAmount.append("5");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_6:
                if (tvBillExpendAmount.getText().toString().equals("0")) {
                    tvBillExpendAmount.setText("6");
                } else {
                    tvBillExpendAmount.append("6");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_7:
                if (tvBillExpendAmount.getText().toString().equals("0")) {
                    tvBillExpendAmount.setText("7");
                } else {
                    tvBillExpendAmount.append("7");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_8:
                if (tvBillExpendAmount.getText().toString().equals("0")) {
                    tvBillExpendAmount.setText("8");
                } else {
                    tvBillExpendAmount.append("8");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_9:
                if (tvBillExpendAmount.getText().toString().equals("0")) {
                    tvBillExpendAmount.setText("9");
                } else {
                    tvBillExpendAmount.append("9");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_0:
                if (tvBillExpendAmount.getText().toString().equals("0")) {
                    //tvBillExpendAmount.setText("1");
                } else {
                    tvBillExpendAmount.append("0");
                    isOverFlow(tvBillExpendAmount);
                }
                break;
            case R.id.bt_expend_Dot:
                String tempEDot = tvBillExpendAmount.getText().toString();
                if (!tempEDot.contains(".")) {
                    tvBillExpendAmount.append(".");
                } else {
                    Toast.makeText(this, "存在小数点", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_expend_Delete:
                tvBillExpendAmount.setText("0");
                break;
            ////////////////////////////////////
            case R.id.bt_expend_Date:
                try {
                    timeSelector();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                pvTime.show(bt_expend_Date);
                break;
            ////////////////////////////////////
            case R.id.bt_expend_Backspace:
                String tempEBackspace = tvBillExpendAmount.getText().toString();
                if (tempEBackspace.equals("0")) {
                    Toast.makeText(this, "已经清空", Toast.LENGTH_SHORT).show();
                } else {
                    tempEBackspace = tempEBackspace.substring(0, tempEBackspace.length() - 1);
                    if (tempEBackspace.isEmpty()) {
                        tvBillExpendAmount.setText("0");
                    } else {
                        tvBillExpendAmount.setText(tempEBackspace);
                    }
                }
                break;
            case R.id.bt_expend_Enter:
                String tempEEnter = tvBillExpendAmount.getText().toString();
                if (tempEEnter.equals("0")) {
                    Toast.makeText(this, "请输入金额", Toast.LENGTH_SHORT).show();
                } else if (expendCategory.isEmpty()) {
                    Toast.makeText(this, "请选择类别", Toast.LENGTH_SHORT).show();
                } else {
                    expendEnter();
                    finish();
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
        }
    }

    private void addLinearLayout() {
        LinearLayout topBar;
        //实例化一个LinearLayout
        topBar = new LinearLayout(this);
        //设置LinearLayout属性(宽和高)
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100);
        topBar.setBackgroundColor(Color.rgb(254,212,54));
        //将以上的属性赋给LinearLayout
        topBar.setLayoutParams(layoutParams);
        this.ltExpend.addView(topBar,0);
    }

    private void getExpendId(){
        tvBillExpendAmount = findViewById(R.id.tv_bill_expend_amount);
        etBillExpendRemark = findViewById(R.id.et_expend_remark);
        bt_expend_1 = findViewById(R.id.bt_expend_1);
        bt_expend_2 = findViewById(R.id.bt_expend_2);
        bt_expend_3 = findViewById(R.id.bt_expend_3);
        bt_expend_4 = findViewById(R.id.bt_expend_4);
        bt_expend_5 = findViewById(R.id.bt_expend_5);
        bt_expend_6 = findViewById(R.id.bt_expend_6);
        bt_expend_7 = findViewById(R.id.bt_expend_7);
        bt_expend_8 = findViewById(R.id.bt_expend_8);
        bt_expend_9 = findViewById(R.id.bt_expend_9);
        bt_expend_0 = findViewById(R.id.bt_expend_0);
        bt_expend_DEL = findViewById(R.id.bt_expend_Delete);
        bt_expend_Date = findViewById(R.id.bt_expend_Date);
        bt_expend_Dot = findViewById(R.id.bt_expend_Dot);
        bt_expend_Enter = findViewById(R.id.bt_expend_Enter);
        bt_expend_BackSpace = findViewById(R.id.bt_expend_Backspace);
        ivBillFood = findViewById(R.id.iv_bill_food);
        ivBillShopping = findViewById(R.id.iv_bill_shopping);
        ivBillDaily = findViewById(R.id.iv_bill_daily);
        ivBillTransfer = findViewById(R.id.iv_bill_transfer);
        ivBillVegetables = findViewById(R.id.iv_bill_vegetables);
        ivBillFruit = findViewById(R.id.iv_bill_fruit);
        ivBillSnacks = findViewById(R.id.iv_bill_snacks);
        ivBillSport = findViewById(R.id.iv_bill_sport);

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

    //获取yy-mm-dd
    private String getTimes(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
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

    private void isOverFlow(TextView textView){
            if (textView.getText().toString().length() == 8){
                Toast.makeText(this, "别买了别买了", Toast.LENGTH_LONG).show();
            }
        }

    private void timeSelector() throws ParseException {

        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();//今天时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2010, 0, 01);//开始时间
        Calendar endDate = Calendar.getInstance();
        endDate.set(2029, 12, 31);//结束时间


        //时间选择器设置属性
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
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

    private void setCategoryIcon(){

        if (intent.getStringExtra("category").equals("餐饮"))
        {ivBillFood.setImageResource(R.drawable.food_on);expendCategory = "餐饮";}
        if (intent.getStringExtra("category").equals("购物"))
        {ivBillShopping.setImageResource(R.drawable.shopping_on);expendCategory = "购物";}
        if (intent.getStringExtra("category").equals("日用"))
        {ivBillDaily.setImageResource(R.drawable.daily_on);expendCategory = "日用";}
        if (intent.getStringExtra("category").equals("交通"))
        {ivBillTransfer.setImageResource(R.drawable.transfer_on);expendCategory = "交通";}
        if (intent.getStringExtra("category").equals("蔬菜"))
        {ivBillVegetables.setImageResource(R.drawable.vegetables_on);expendCategory = "蔬菜";}
        if (intent.getStringExtra("category").equals("水果"))
        {ivBillFruit.setImageResource(R.drawable.fruit_on);expendCategory = "水果";}
        if (intent.getStringExtra("category").equals("运动"))
        {ivBillSport.setImageResource(R.drawable.sport_on);expendCategory = "运动";}
    }
}
