package com.example.narcissus.ui.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.narcissus.databinding.FragmentContactBinding;

public class ContactFragment extends Fragment {

    private FragmentContactBinding binding;
    private ContactViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        binding = FragmentContactBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.sendMessage.setOnClickListener(view -> doValidate());

        viewModel.getCreateError().observe(getActivity(), this::createError);
        viewModel.getInvalidField().observe(getActivity(), this::invalidField);
        viewModel.getCreateSuccess().observe(getActivity(), this::createSuccess);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void doValidate() {
        String phone = binding.phone.getText().toString();
        String email = binding.email.getText().toString();
        String message = binding.message.getText().toString();
        String name = binding.name.getText().toString();

        viewModel.validate(name, email, phone, message);
    }

    private void createError(String message) {
        Toast.makeText(getContext(), "Erro ao enviar a mensagem !!!", Toast.LENGTH_LONG).show();
    }

    private void invalidField(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    private void createSuccess(Void avoid) {
        Toast.makeText(getContext(), "Mensagem enviada com sucesso !!!", Toast.LENGTH_LONG).show();

        binding.phone.setText("");
        binding.email.setText("");
        binding.message.setText("");
        binding.name.setText("");
    }

}