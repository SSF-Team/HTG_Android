package com.chuhelan.htg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 跳转去主页
        Intent main = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(main);   // 启动
        this.finish();    // 关闭自身防止返回
    }
}