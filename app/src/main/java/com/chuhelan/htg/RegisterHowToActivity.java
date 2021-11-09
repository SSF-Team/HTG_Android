package com.chuhelan.htg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chuhelan.htg.ui.home.HomeFragment;

public class RegisterHowToActivity extends BaseActivity {

    private TextView register_how_to_login;
    private Button register_how_to_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_how_to);
        register_how_to_login = findViewById(R.id.register_how_to_login);
        register_how_to_register = findViewById(R.id.register_how_to_register);
        Onclick onClick = new Onclick();
        register_how_to_login.setOnClickListener(onClick);
        register_how_to_register.setOnClickListener(onClick);
    }

    private class Onclick implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.register_how_to_login:
                    navigateTo(LoginActivity.class);
                    break;
                case R.id.register_how_to_register:
                    navigateTo(RegisterAvtivity.class);
                    break;
            }
        }
    }


}