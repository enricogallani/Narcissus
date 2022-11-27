package com.example.narcissus.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.narcissus.databinding.FragmentProcedureBinding;
import com.example.narcissus.ui.LoginActivity;

public class LogoutFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();

        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}