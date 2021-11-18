package com.chuhelan.htg.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chuhelan.htg.LoginActivity;
import com.chuhelan.htg.R;
import com.chuhelan.htg.RegisterHowToActivity;
import com.chuhelan.htg.databinding.FragmentHomeBinding;

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

    private TextView home_login, home_register, home_tourer, home_line;
//    private ImageView scan;
//    private EditText editScan;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        OnClick onClick = new OnClick();
        doVerify();
        super.onActivityCreated(savedInstanceState);
        home_login = getActivity().findViewById(R.id.home_login);
        home_register = getActivity().findViewById(R.id.home_register);
        home_tourer = getActivity().findViewById(R.id.home_tourer);
        home_line = getActivity().findViewById(R.id.home_line);
//        editScan = getActivity().findViewById(R.id.edit_scan);
//        scan = getActivity().findViewById(R.id.scan);
        home_login.setOnClickListener(onClick);
        home_register.setOnClickListener(onClick);
    }


    private void doVerify() {
        SharedPreferences sp = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (sp.getString("token", "") != null) {
//                    home_login.setVisibility(View.GONE);
//                    home_line.setVisibility(View.GONE);
//                    home_register.setVisibility(View.GONE);
                    home_tourer.setText(sp.getString("user_first_name", ""));
                } else {
                    home_login.setVisibility(View.VISIBLE);
                    home_line.setVisibility(View.VISIBLE);
                    home_register.setVisibility(View.VISIBLE);
                }
            }
        });
        thread.start();
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