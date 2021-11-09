package com.chuhelan.htg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotLoginActivity extends BaseActivity {

    private Button not_login_login, not_login_register;
    Onclick onClick = new Onclick();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_login);
        not_login_login = findViewById(R.id.not_login_login);
        not_login_register = findViewById(R.id.not_login_register);
        not_login_login.setOnClickListener(onClick);
        not_login_register.setOnClickListener(onClick);


    }

    private class Onclick implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.not_login_login:
                    navigateTo(LoginActivity.class);
                    break;
                case R.id.not_login_register:
                    navigateTo(RegisterAvtivity.class);
                    break;
            }
        }
    }
}