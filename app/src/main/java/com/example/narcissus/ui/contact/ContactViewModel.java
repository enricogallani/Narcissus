package com.example.narcissus.ui.contact;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.narcissus.data.Appointment;
import com.example.narcissus.data.Message;
import com.example.narcissus.data.SuccessMessage;
import com.example.narcissus.repository.ApiClient;
import com.example.narcissus.repository.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactViewModel extends ViewModel {

    private final MutableLiveData<String> invalidField;
    private final MutableLiveData<String> createError;
    private final MutableLiveData<Void> createSuccess;
    private final ApiInterface api;

    public ContactViewModel() {
        invalidField = new MutableLiveData<>();
        createError = new MutableLiveData<>();
        createSuccess = new MutableLiveData<>();
        api = ApiClient.getClient().create(ApiInterface.class);
    }

    public void validate(String name, String email, String phone, String message) {
        if (name.isEmpty()) {
            invalidField.setValue("Nome é obrigatório !!!");
            return;
        }

        if (email.isEmpty()) {
            invalidField.setValue("E-mail é obrigatório !!!");
            return;
        }

        if (phone.isEmpty()) {
            invalidField.setValue("Telefone é obrigatório !!!");
            return;
        }

        if (message.isEmpty()) {
            invalidField.setValue("Mensagem é obrigatório !!!");
            return;
        }

        final Message msg = new Message();
        msg.setName(name);
        msg.setEmail(email);
        msg.setPhone(phone);
        msg.setMessage(message);

        callApi(msg);
    }

    private void callApi(Message message) {
        final Call<SuccessMessage> call = api.createMessage(message);
        call.enqueue(new Callback<SuccessMessage>() {
            @Override
            public void onResponse(Call<SuccessMessage> call, Response<SuccessMessage> response) {
                createSuccess.setValue(null);
            }

            @Override
            public void onFailure(@NonNull Call<SuccessMessage> call, Throwable t) {
                createError.setValue(t.getMessage());
            }
        });
    }

    public MutableLiveData<String> getInvalidField() {
        return invalidField;
    }

    public MutableLiveData<String> getCreateError() {
        return createError;
    }

    public MutableLiveData<Void> getCreateSuccess() {
        return createSuccess;
    }
}