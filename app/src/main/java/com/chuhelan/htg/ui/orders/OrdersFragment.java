package com.chuhelan.htg.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.chuhelan.htg.databinding.FragmentOrdersBinding;

public class OrdersFragment extends Fragment {

    private FragmentOrdersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
//        OrdersViewModel ordersViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);

    binding = FragmentOrdersBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

//        final TextView textView = binding.textDashboard;
//        ordersViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}