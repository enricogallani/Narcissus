package com.example.narcissus.ui.procedure;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.narcissus.databinding.FragmentContactBinding;
import com.example.narcissus.databinding.FragmentProcedureBinding;
import com.example.narcissus.ui.contact.ContactViewModel;

public class ProcedureFragment extends Fragment {

    private FragmentProcedureBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProcedureBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}