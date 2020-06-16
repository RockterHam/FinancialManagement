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

public class UpdateIncomeAttributes extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout ltIncome;
    private Button bt_income_DEL , bt_income_Date , bt_income_1 , bt_income_2 , bt_income_3 ,
            bt_income_4 , bt_income_5 , bt_income_6 , bt_income_7 , bt_income_8 , bt_income_9 ,
            bt_income_0 , bt_income_Dot , bt_income_Enter , bt_income_BackSpace;
    private TextView tvBillIncomeAmount;
    private EditText etBillIncomeRemark;
    private TimePickerView pvTime;
    private ImageView ivBillSalary , ivBillWage , ivBillStock , ivBillGift , ivBillOthers;
    private String incomeCategory = "";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.income_fragment);
        intent = getIntent();
        Date date = new Date();
        ltIncome = findViewById(R.id.lt_income);
        addLinearLayout();

        getIncomeId();
        setIncomeListener();
        bt_income_Date.setText(getTimes(date));

        setCategoryIcon();
        tvBillIncomeAmount.setText(intent.getStringExtra("amount").replace("-",""));

        bt_income_Date.setText(intent.getStringExtra("date"));
        etBillIncomeRemark.setText(intent.getStringExtra("remark"));

    }

    //向数据库写入income
    private void incomeEnter() {
        SQLiteOperation sqLiteOperation = new SQLiteOperation();
        if (!(tvBillIncomeAmount.getText().equals(intent.getStringExtra("amount")))){
            sqLiteOperation.update(this,intent.getStringExtra("UUID"),"amount",tvBillIncomeAmount.getText().toString());
        }
        if (!(bt_income_Date.getText().equals(intent.getStringExtra("date")))){
            sqLiteOperation.update(this,intent.getStringExtra("UUID"),"date",bt_income_Date.getText().toString());
        }
        if (!(incomeCategory.equals(intent.getStringExtra("category")))){
            sqLiteOperation.update(this,intent.getStringExtra("UUID"),"category",incomeCategory);
        }
        if (!(etBillIncomeRemark.getText().equals(intent.getStringExtra("remark")))){
            sqLiteOperation.update(this,intent.getStringExtra("UUID"),"remark",etBillIncomeRemark.getText().toString());
        }
    }

    @Override
    public void onClick(View view) {
        int v = view.getId();
        switch (v){
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
                    Toast.makeText(this,"存在小数点", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_income_Delete:
                tvBillIncomeAmount.setText("0");
                break;
            ////////////////////////////////////
            case R.id.bt_income_Date:
                try {
                    timeSelector();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                pvTime.show(bt_income_Date);
                break;
            ////////////////////////////////////
            case R.id.bt_income_Backspace:
                String tempIBackspace = tvBillIncomeAmount.getText().toString();
                if (tempIBackspace.equals("0")){
                    Toast.makeText(this,"已经清空", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(this,"请输入金额", Toast.LENGTH_SHORT).show();
                } else if (incomeCategory.isEmpty()){
                    Toast.makeText(this,"请选择类别", Toast.LENGTH_SHORT).show();
                } else {
                    incomeEnter();
                    finish();
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

    private void addLinearLayout() {
        LinearLayout topBar;
        //实例化一个LinearLayout
        topBar = new LinearLayout(this);
        //设置LinearLayout属性(宽和高)
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100);
        topBar.setBackgroundColor(Color.rgb(254,212,54));
        //将以上的属性赋给LinearLayout
        topBar.setLayoutParams(layoutParams);
        this.ltIncome.addView(topBar,0);
    }

    private void getIncomeId(){
        tvBillIncomeAmount = findViewById(R.id.tv_bill_income_amount);
        etBillIncomeRemark = findViewById(R.id.et_income_remark);
        bt_income_1 = findViewById(R.id.bt_income_1);
        bt_income_2 = findViewById(R.id.bt_income_2);
        bt_income_3 = findViewById(R.id.bt_income_3);
        bt_income_4 = findViewById(R.id.bt_income_4);
        bt_income_5 = findViewById(R.id.bt_income_5);
        bt_income_6 = findViewById(R.id.bt_income_6);
        bt_income_7 = findViewById(R.id.bt_income_7);
        bt_income_8 = findViewById(R.id.bt_income_8);
        bt_income_9 = findViewById(R.id.bt_income_9);
        bt_income_0 = findViewById(R.id.bt_income_0);
        bt_income_DEL = findViewById(R.id.bt_income_Delete);
        bt_income_Date = findViewById(R.id.bt_income_Date);
        bt_income_Dot = findViewById(R.id.bt_income_Dot);
        bt_income_Enter = findViewById(R.id.bt_income_Enter);
        bt_income_BackSpace = findViewById(R.id.bt_income_Backspace);
        ivBillSalary = findViewById(R.id.iv_bill_salary);
        ivBillWage = findViewById(R.id.iv_bill_wage);
        ivBillStock = findViewById(R.id.iv_bill_stock);
        ivBillGift = findViewById(R.id.iv_bill_gift);
        ivBillOthers = findViewById(R.id.iv_bill_others);
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

    //获取yy-mm-dd
    private String getTimes(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private void clearIncomeSelect(){
        ivBillSalary.setImageResource(R.drawable.salary_off);
        ivBillWage.setImageResource(R.drawable.wage_off);
        ivBillStock.setImageResource(R.drawable.stock_off);
        ivBillGift.setImageResource(R.drawable.gift_off);
        ivBillOthers.setImageResource(R.drawable.others_off);
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
                bt_income_Date.setText(getTimes(date));//回调后得到的date放入dateStr存储，yy-mm-dd
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

        if (intent.getStringExtra("category").equals("工资"))
        {ivBillSalary.setImageResource(R.drawable.salary_on);incomeCategory = "工资";}
        if (intent.getStringExtra("category").equals("兼职"))
        {ivBillWage.setImageResource(R.drawable.wage_on);incomeCategory = "兼职";}
        if (intent.getStringExtra("category").equals("理财"))
        {ivBillStock.setImageResource(R.drawable.stock_on);incomeCategory = "理财";}
        if (intent.getStringExtra("category").equals("礼金"))
        {ivBillGift.setImageResource(R.drawable.gift_on);incomeCategory = "礼金";}
        if (intent.getStringExtra("category").equals("其它"))
        {ivBillOthers.setImageResource(R.drawable.others_on);incomeCategory = "其它";}
    }
}
