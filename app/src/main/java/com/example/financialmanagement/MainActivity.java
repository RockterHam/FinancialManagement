package com.example.financialmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //底部菜单栏3个LinearLayout
    private View ltHome;
    private View ltStatistics;
    private View ltUser;

    //底部菜单栏3个ImageView
    private ImageView ivHome;
    private ImageView ivStatistics;
    private ImageView ivUser;

    //3个Fragment
    private Fragment fmHome;
    private Fragment fmStatistics;
    private Fragment fmUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //初始化
        init();
        //设置第一个Fragment默认显示
        setFragment(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                break;
            case R.id.lt_home:
                setFragment(0);
                break;
            case R.id.lt_statistics:
                setFragment(1);
                break;
            case R.id.lt_user:
                setFragment(2);
                break;
        }
    }

    private void init(){
        //初始化控件
        ltHome = (LinearLayout)findViewById(R.id.lt_home);
        ltStatistics = (LinearLayout)findViewById(R.id.lt_statistics);
        ltUser = (LinearLayout)findViewById(R.id.lt_user);

        ivHome = (ImageView)findViewById(R.id.iv_home);
        ivStatistics = (ImageView)findViewById(R.id.iv_statistics);
        ivUser = (ImageView)findViewById(R.id.iv_user);

        //设置监听
        ltHome.setOnClickListener(this);
        ltStatistics.setOnClickListener(this);
        ltUser.setOnClickListener(this);
    }

    private void setFragment(int index){
        //获取Fragment管理器
        FragmentManager mFragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
        //隐藏所有Fragment
        hideFragments(mTransaction);
        switch (index){
            default:
                break;
            case 0:
                //设置菜单栏为选中状态（修改文字和图片颜色）
               /* mImageHome.setTextColor(getResources()
                        .getColor(R.color.colorTextPressed));*/
                ivHome.setImageResource(R.drawable.ic_home_on);
                //显示对应Fragment
                if(fmHome == null){
                    fmHome = new HomeFragment();
                    mTransaction.add(R.id.home_container, fmHome, "Home_fragment");
                }else {
                    mTransaction.show(fmHome);
                }
                break;
            case 1:
              /*  mImageOrder.setTextColor(getResources()
                        .getColor(R.color.colorTextPressed));*/
                ivStatistics.setImageResource(R.drawable.ic_statistics_on);
                if(fmStatistics == null){
                    //fmStatistics = new StatisticsFragment();
                    mTransaction.add(R.id.home_container, fmStatistics, "Statistics_fragment");
                }else {
                    mTransaction.show(fmStatistics);
                }
                break;
            case 2:
              /*  mImageCommunity.setTextColor(getResources()
                        .getColor(R.color.colorTextPressed));*/
                ivUser.setImageResource(R.drawable.ic_user_on);
                if(fmUser == null){
                    fmUser = new UserFragment();
                    mTransaction.add(R.id.home_container, fmUser, "community_fragment");
                }else {
                    mTransaction.show(fmUser);
                }
                break;
        }
        //提交事务
        mTransaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction){
        if(fmHome != null){
            //隐藏Fragment
            transaction.hide(fmHome);
            //将对应菜单栏设置为默认状态
          /*  mImageHome.setTextColor(getResources()
                    .getColor(R.color.colorText));*/
            ivHome.setImageResource(R.drawable.ic_home_off);
        }
        if(fmStatistics != null){
            transaction.hide(fmStatistics);
           /* mImageOrder.setTextColor(getResources()
                    .getColor(R.color.colorText));*/
            ivStatistics.setImageResource(R.drawable.ic_statistics_off);
        }
        if(fmUser != null){
            transaction.hide(fmUser);
            /*mImageCommunity.setTextColor(getResources()
                    .getColor(R.color.colorText));*/
            ivUser.setImageResource(R.drawable.ic_user_off);
        }
    }
    
}
