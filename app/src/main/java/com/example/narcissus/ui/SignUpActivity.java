package com.example.narcissus.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.narcissus.R;
import com.example.narcissus.data.User;
import com.example.narcissus.databinding.CadastroBinding;
import com.example.narcissus.ui.viewmodel.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {

    private SignUpViewModel viewModel;
    private CadastroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        binding = CadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.signup.setOnClickListener(v -> {
            Log.d("LSDLKSDL", "LSKDLSKDLKDL");
            String user = binding.email.getText().toString();
            String pass = binding.password.getText().toString();
            String name = binding.name.getText().toString();
            String cpf = binding.cpf.getText().toString();

            viewModel.signup(name, user, cpf, pass);

            viewModel.getUserEmpty().observe(this, this::userEmpty);
            viewModel.getPassEmpty().observe(this, this::passwordEmpty);
            viewModel.getNameEmpty().observe(this, this::nameEmpty);
            viewModel.getSignupSuccess().observe(this, this::signUpSuccess);
            viewModel.getSignupError().observe(this, this::signupError);
        });
    }

    private void userEmpty(Void avoid) {
        Toast.makeText(getApplicationContext(), getString(R.string.empty_user), Toast.LENGTH_LONG).show();
    }

    private void passwordEmpty(Void avoid) {
        Toast.makeText(getApplicationContext(), getString(R.string.empty_password), Toast.LENGTH_LONG).show();
    }

    private void nameEmpty(Void avoid) {
        Toast.makeText(getApplicationContext(), getString(R.string.empty_name), Toast.LENGTH_LONG).show();
    }

    private void signUpSuccess(User user) {
        Toast.makeText(getApplicationContext(), getString(R.string.correct_value), Toast.LENGTH_LONG).show();
    }

    private void signupError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}