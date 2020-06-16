package com.example.financialmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BillAttributes extends AppCompatActivity {

    private TextView tvAttriCategory , tvAttriExIn , tvAttriAmount , tvAttriDate , tvAttriRemark ,
            tvAttriEdit , tvAttriDel;
    private ImageView ivAttriCategory;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_billattributes);

        SQLiteOperation sqLiteOperation = new SQLiteOperation();
        tvAttriCategory = findViewById(R.id.tv_attri_category);
        tvAttriExIn = findViewById(R.id.tv_attri_ex_in);
        tvAttriAmount = findViewById(R.id.tv_attri_amount);
        tvAttriDate = findViewById(R.id.tv_attri_date);
        tvAttriRemark = findViewById(R.id.tv_attri_remark);
        tvAttriEdit = findViewById(R.id.tv_attri_edit);
        tvAttriDel = findViewById(R.id.tv_attri_del);
        ivAttriCategory = findViewById(R.id.iv_attri_category);

        Intent intent = getIntent();
        System.out.println(intent.getStringExtra("UUID")+"启动后");

        category = intent.getStringExtra("category");
        tvAttriCategory.setText(category);

        tvAttriDate.setText(intent.getStringExtra("date"));
        tvAttriRemark.setText(intent.getStringExtra("remark"));

        if (intent.getStringExtra("amount").contains("-")){
            tvAttriExIn.setText("支出");
            tvAttriAmount.setText(intent.getStringExtra("amount").replace("-",""));
        }else{
            tvAttriExIn.setText("收入");
            tvAttriAmount.setText(intent.getStringExtra("amount"));
        }
        setIcon();

        tvAttriEdit.setOnClickListener(v -> {

        });
        tvAttriDel.setOnClickListener(v -> {
            sqLiteOperation.delete(this,"Info",intent.getStringExtra("UUID"));

        });
    }

    private void setIcon(){
        //控制图标
        if (category.equals("餐饮")){
            ivAttriCategory.setImageResource(R.drawable.food_off);
        }
        if (category.equals("购物")){
            ivAttriCategory.setImageResource(R.drawable.shopping_off);
        }
        if (category.equals("日用")){
            ivAttriCategory.setImageResource(R.drawable.daily_off);
        }
        if (category.equals("交通")){
            ivAttriCategory.setImageResource(R.drawable.transfer_off);
        }
        if (category.equals("蔬菜")){
            ivAttriCategory.setImageResource(R.drawable.vegetables_off);
        }
        if (category.equals("水果")){
            ivAttriCategory.setImageResource(R.drawable.fruit_off);
        }
        if (category.equals("零食")){
            ivAttriCategory.setImageResource(R.drawable.snacks_off);
        }
        if (category.equals("运动")){
            ivAttriCategory.setImageResource(R.drawable.sport_off);
        }
        if (category.equals("工资")){
            ivAttriCategory.setImageResource(R.drawable.salary_off);
        }
        if (category.equals("兼职")){
            ivAttriCategory.setImageResource(R.drawable.wage_off);
        }
        if (category.equals("理财")){
            ivAttriCategory.setImageResource(R.drawable.stock_off);
        }
        if (category.equals("礼金")){
            ivAttriCategory.setImageResource(R.drawable.gift_off);
        }
        if (category.equals("其它")){
            ivAttriCategory.setImageResource(R.drawable.others_off);
        }
    }
}
