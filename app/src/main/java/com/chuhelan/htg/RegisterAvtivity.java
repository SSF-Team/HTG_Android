package com.chuhelan.htg;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chuhelan.htg.util.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterAvtivity extends BaseActivity {

    private Button register_right_now;
    private EditText ming, xing, phone, email, password;
    private boolean register_flag = false;

    String register_ming, register_xing, register_phone, register_email, register_password;

    OnClick onclick = new OnClick();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_avtivity);
        register_right_now = findViewById(R.id.register_right_now);
        ming = findViewById(R.id.ming);
        xing = findViewById(R.id.xing);
        phone = findViewById(R.id.register_phone);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        register_right_now.setOnClickListener(onclick);


    }

    private class OnClick implements View.OnClickListener {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.register_right_now:
                    register_ming = ming.getText().toString();
                    register_xing = xing.getText().toString();
                    register_phone = phone.getText().toString();
                    register_email = email.getText().toString();
                    register_password = password.getText().toString();
                    doRegister();
                    break;
            }
        }
    }

    public void doRegister() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "https://htg.chuhelan.com/register?user_last_name=" + register_xing + "&user_first_name=" +
                        register_ming + "&user_email=" + register_email + "&user_password=" + register_password + "&user_phone=" + register_phone;
                System.out.println("==================");
                System.out.println(result);
                System.out.println("==================");
                String register_url = HttpUtils.getJsonContent(result);
                System.out.println("url: " + register_url);
                try {
                    JSONObject register_user = new JSONObject(register_url);
                    System.out.println("register_user: " + register_user.toString());
                    if (register_user.getInt("code") == 200) {
                        register_flag = true;
                        navigateTo(HomeActivity.class);
                    } else {
                        register_flag = false;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (register_flag == true) {
            showToast("注册成功！");
        } else {
            showToast("注册失败！");
        }
    }

}