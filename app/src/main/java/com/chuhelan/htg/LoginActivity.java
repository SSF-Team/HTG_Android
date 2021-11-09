package com.chuhelan.htg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends BaseActivity {

    private TextView login_register;
    private Button login_login;

    Onclick onclick = new Onclick();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_register = findViewById(R.id.login_register);
        login_register.setOnClickListener(onclick);
    }

    private class Onclick implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_register:
                    navigateTo(RegisterAvtivity.class);
                    break;
            }
        }
    }

}