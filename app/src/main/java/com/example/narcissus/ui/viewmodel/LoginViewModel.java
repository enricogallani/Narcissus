package com.example.narcissus.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.narcissus.data.SuccessMessage;
import com.example.narcissus.data.User;
import com.example.narcissus.repository.ApiClient;
import com.example.narcissus.repository.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<Void> userEmpty;
    private final MutableLiveData<Void> passEmpty;
    private final MutableLiveData<Void> loginInvalid;
    private final MutableLiveData<User> loginSuccess;
    private final ApiInterface api;

    public LoginViewModel() {
        userEmpty = new MutableLiveData<>();
        passEmpty = new MutableLiveData<>();
        loginInvalid = new MutableLiveData<>();
        loginSuccess = new MutableLiveData<>();
        api = ApiClient.getClient().create(ApiInterface.class);
    }

    private void callApi(User user) {
        final Call<SuccessMessage> call = api.login(user);
        call.enqueue(new Callback<SuccessMessage>() {
            @Override
            public void onResponse(Call<SuccessMessage> call, Response<SuccessMessage> response) {
                loginSuccess.setValue(null);
            }

            @Override
            public void onFailure(Call<SuccessMessage> call, Throwable t) {
                Log.d("LSKDLDSKDSLKDLS", "SLDKLSDKLSD " + t.getMessage());
                loginInvalid.setValue(null);
            }
        });
    }

    public void login(String email, String password) {
        if (email.isEmpty()) {
            userEmpty.setValue(null);
        } else if (password.isEmpty()) {
            passEmpty.setValue(null);
        } else {
            final User user = new User();
            user.setEmail(email);
            user.setPassword(password);

            callApi(user);
        }
    }

    public MutableLiveData<Void> getUserEmpty() {
        return userEmpty;
    }

    public MutableLiveData<Void> getPassEmpty() {
        return passEmpty;
    }

    public MutableLiveData<Void> getLoginInvalid() {
        return loginInvalid;
    }

    public MutableLiveData<User> getLoginSuccess() {
        return loginSuccess;
    }
}