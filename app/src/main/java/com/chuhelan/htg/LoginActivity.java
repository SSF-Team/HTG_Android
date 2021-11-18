package com.chuhelan.htg;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.chuhelan.htg.ui.home.HomeFragment;
import com.chuhelan.htg.util.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity {

    private TextView login_register;
    private Button login_login;
    private EditText login_email, login_password;
    private boolean password_currect = false;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    Onclick onclick = new Onclick();
    String user_email, user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        verifyStoragePermissions(this);
        login_register = findViewById(R.id.login_register);
        login_register.setOnClickListener(onclick);
        login_login = findViewById(R.id.login_login);
        login_login.setOnClickListener(onclick);
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
    }

    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class Onclick implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_register:
                    navigateTo(RegisterAvtivity.class);
                    break;
                case R.id.login_login:
                    user_email = login_email.getText().toString();
                    user_password = login_password.getText().toString();
                    doLogin();
                    break;
            }
        }
    }

    public void doLogin() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = HttpUtils.getJsonContent("https://htg.chuhelan.com/login?mail=" + user_email + "&password=" + user_password);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    System.out.println(jsonObject.toString());
//                    save token
//                    get token
                    String Token = jsonObject.getString("message").split("\\|")[0];
//                    get user_id
                    int user_id = Integer.parseInt(jsonObject.getString("message").split("\\|")[1]);
//                    get user_info
                    String user_profile_link = HttpUtils.getJsonContent("https://htg.chuhelan.com/info/base?id=" + user_id);
                    JSONObject do_user_profile = new JSONObject(user_profile_link);
//                    头衔地址 用户名和姓
                    String user_picLink = do_user_profile.getString("pic_link");
                    String user_first_name = do_user_profile.getString("first_name");
                    String user_last_name = do_user_profile.getString("last_name");

                    System.out.println("用户token:" + Token);
                    System.out.println("用户信息：" + user_picLink + "," + user_first_name + "," + user_last_name);

                    SharedPreferences sp = getSharedPreferences("token", 0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", Token);
                    editor.putString("user_picLink", user_picLink);
                    editor.putString("user_first_name", user_first_name);
                    editor.putString("user_last_name", user_last_name);
                    editor.commit();

                    if (jsonObject.getInt("code") == 200) {
                        password_currect = true;
                        navigateTo(HomeFragment.class);
                    } else {
                        password_currect = false;
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
        if (password_currect == true) {
            showToast("登录成功！");
        } else {
            showToast("登陆失败！");
        }
    }

}