package com.chuhelan.htg.ui.home;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.chuhelan.htg.LoginActivity;
import com.chuhelan.htg.MainActivity;
import com.chuhelan.htg.R;
import com.chuhelan.htg.RegisterHowToActivity;
import com.chuhelan.htg.databinding.FragmentHomeBinding;
import com.chuhelan.htg.util.Capture;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Objects;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    private TextView home_login, home_register;
    private ImageView scan;
    private EditText editScan;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        OnClick onClick = new OnClick();
        super.onActivityCreated(savedInstanceState);
        home_login = getActivity().findViewById(R.id.home_login);
        home_register = getActivity().findViewById(R.id.home_register);
        editScan = getActivity().findViewById(R.id.edit_scan);
        scan = getActivity().findViewById(R.id.scan);
        home_login.setOnClickListener(onClick);
        home_register.setOnClickListener(onClick);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.initiateScan();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 跳转扫描页面返回扫描数据
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //  判断返回值是否为空
        if (scanResult != null) {
            //返回条形码数据
            String result = scanResult.getContents();
            Log.d("code", result);
            editScan.setText(result);
        }
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.home_login:
                    intent = new Intent(getContext(), LoginActivity.class);
                    break;
                case R.id.home_register:
                    intent = new Intent(getContext(), RegisterHowToActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}