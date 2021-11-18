package com.chuhelan.htg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuhelan.htg.util.BitmapUtil;
import com.chuhelan.htg.util.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class NavigationViewHead extends BaseActivity {

    private ImageView navigation_user_profile;
    private TextView navigation_name, navigation_title;
    private String user_name;
    private Bitmap user_profile;
    private String vip_title = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view_head);

        navigation_user_profile = findViewById(R.id.navigation_user_profile);
        navigation_name = findViewById(R.id.navigation_name);
        navigation_title = findViewById(R.id.navigation_title);



    }

    public void setProfile() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("token", Context.MODE_PRIVATE);
                try {
                    vip_title = new JSONObject(HttpUtils.getJsonContent("https://htg.chuhelan.com/vip/title")).toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                user_name = sp.getString("user_first_name", "");
                user_profile = BitmapUtil.getHttpBitmap(sp.getString("user_picLink", ""));

                System.out.println("------------------侧栏操作--------------------------");
                System.out.println("获取到的vip标题是：" + vip_title);
                System.out.println("获取到的用户名为：" + user_name);
                System.out.println("--------------------------------------------");

                navigation_title.setText(vip_title);
                navigation_user_profile.setImageBitmap(user_profile);
                navigation_name.setText(user_name);
            }
        });
        thread.start();

    }
}