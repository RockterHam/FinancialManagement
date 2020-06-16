package com.example.financialmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.financialmanagement.R;

public class Login extends AppCompatActivity {

    Button btLoginEnter, btLoginForget;
    EditText edLoginPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        btLoginEnter = findViewById(R.id.bt_login_enter);
        btLoginForget = findViewById(R.id.bt_login_forget);

        btLoginEnter.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        btLoginForget.setOnClickListener(v -> {
            finish();
        });

    }
}
