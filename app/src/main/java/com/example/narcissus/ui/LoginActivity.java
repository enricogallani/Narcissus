package com.example.narcissus.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.narcissus.R;
import com.example.narcissus.data.User;
import com.example.narcissus.databinding.LoginBinding;
import com.example.narcissus.ui.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel viewModel;
    private LoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signup.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
        });

        binding.login.setOnClickListener(v -> {
            String user = binding.user.getText().toString();
            String password = binding.password.getText().toString();

            //viewModel.login(user, password);
            final Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            //intent.putExtra("user", user);

            startActivity(intent);
        });

        viewModel.getUserEmpty().observe(this, this::userEmpty);
        viewModel.getPassEmpty().observe(this, this::passwordEmpty);
        viewModel.getLoginInvalid().observe(this, this::loginInvalid);
        viewModel.getLoginSuccess().observe(this, this::loginSuccess);
    }

    private void userEmpty(Void avoid) {
        Toast.makeText(getApplicationContext(), getString(R.string.empty_user), Toast.LENGTH_LONG).show();
    }

    private void passwordEmpty(Void avoid) {
        Toast.makeText(getApplicationContext(), getString(R.string.empty_password), Toast.LENGTH_LONG).show();
    }

    private void loginInvalid(Void avoid) {
        Toast.makeText(getApplicationContext(), getString(R.string.incorrect_value), Toast.LENGTH_LONG).show();
    }

    private void loginSuccess(User user) {
        final Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.putExtra("user", user);

        startActivity(intent);
    }
}