package com.example.narcissus.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.narcissus.data.SuccessMessage;
import com.example.narcissus.data.User;
import com.example.narcissus.repository.ApiClient;
import com.example.narcissus.repository.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {

    private final MutableLiveData<Void> userEmpty;
    private final MutableLiveData<Void> passEmpty;
    private final MutableLiveData<Void> nameEmpty;
    private final MutableLiveData<User> signupSuccess;
    private final MutableLiveData<String> signupError;
    private final ApiInterface api;

    public SignUpViewModel() {
        userEmpty = new MutableLiveData<>();
        passEmpty = new MutableLiveData<>();
        nameEmpty = new MutableLiveData<>();
        signupSuccess = new MutableLiveData<>();
        signupError = new MutableLiveData<>();
        api = ApiClient.getClient().create(ApiInterface.class);
    }

    private void callApi(User user) {
        final Call<SuccessMessage> call = api.signup(user);
        call.enqueue(new Callback<SuccessMessage>() {
            @Override
            public void onResponse(Call<SuccessMessage> call, Response<SuccessMessage> response) {
                signupSuccess.setValue(new User());
            }

            @Override
            public void onFailure(@NonNull Call<SuccessMessage> call, Throwable t) {
                signupError.setValue(t.getMessage());
            }
        });
    }

    public void signup(String name, String email, String cpf, String password) {
        if (email.isEmpty()) {
            userEmpty.setValue(null);
        } else if (name.isEmpty()) {
            nameEmpty.setValue(null);
        } else if (password.isEmpty()) {
            passEmpty.setValue(null);
        } else {
            final User user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setCpf(cpf);
            user.setEmail(email);
            callApi(user);
        }
    }

    public MutableLiveData<Void> getUserEmpty() {
        return userEmpty;
    }

    public MutableLiveData<Void> getPassEmpty() {
        return passEmpty;
    }

    public MutableLiveData<Void> getNameEmpty() {
        return nameEmpty;
    }

    public MutableLiveData<User> getSignupSuccess() {
        return signupSuccess;
    }

    public MutableLiveData<String> getSignupError() {
        return signupError;
    }
}