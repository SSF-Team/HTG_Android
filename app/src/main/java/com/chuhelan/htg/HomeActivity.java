package com.chuhelan.htg;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chuhelan.htg.util.Capture;
import com.chuhelan.htg.util.HttpUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.chuhelan.htg.databinding.ActivityHomeBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends BaseActivity {

    private ActivityHomeBinding binding;
    private boolean isSideOpen = false;
    private ImageView scan, search;
    private EditText search_edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.getSupportActionBar().hide();      // 隐藏标题栏

        scan = findViewById(R.id.scan);
        search_edit = findViewById(R.id.search_edit);
        search = findViewById(R.id.search_order);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String searchUrl = HttpUtils.getJsonContent("https://htg.chuhelan.com/order/get/" + search_edit.getText());
//                try {
//                    JSONObject jsonObject = new JSONObject(searchUrl);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                navigateTo(SearchOrder.class);
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(HomeActivity.this);
                integrator.setOrientationLocked(true);
                integrator.setCaptureActivity(Capture.class);
                integrator.initiateScan();
            }
        });

        // Dock 栏切换逻辑
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_orders, R.id.navigation_mine
        ).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // 侧边栏按钮事件
        Button bOpenTb = findViewById(R.id.open_sidebar);
        bOpenTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout navDrawer = findViewById(R.id.drawer_main);
                if (isSideOpen) {
                    navDrawer.closeDrawer(Gravity.LEFT);
                } else {
                    navDrawer.openDrawer(Gravity.LEFT);
                }
            }
        });

        // 侧边栏收回事件
        DrawerLayout navDrawer = findViewById(R.id.drawer_main);
        navDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                System.out.println(findViewById(R.id.search_edit).getWidth());
                if (newState == 2) {
//                    ObjectAnimator animation;
//                    if (isSideOpen) {
//                        // 动画
//                        animation = ObjectAnimator.ofFloat(findViewById(R.id.open_sidebar), "translationX", -30, 0);
//                    } else {
//                        // 动画
//                        animation = ObjectAnimator.ofFloat(findViewById(R.id.open_sidebar), "translationX", 0, -30);
//                    }
//                    animation.setDuration(400);
//                    animation.start();
                    isSideOpen = !isSideOpen;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 跳转扫描页面返回扫描数据
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //  判断返回值是否为空
        if (scanResult != null) {
            //返回条形码数据
            String result = scanResult.getContents();
            Log.d("code", result);
            search_edit.setText(result);
        }
    }

}