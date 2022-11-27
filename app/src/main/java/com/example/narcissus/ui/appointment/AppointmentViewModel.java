package com.example.narcissus.ui.appointment;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.narcissus.data.Appointment;
import com.example.narcissus.data.SuccessMessage;
import com.example.narcissus.repository.ApiClient;
import com.example.narcissus.repository.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentViewModel extends ViewModel {

    private final MutableLiveData<Void> createInvalid;
    private final MutableLiveData<String> createError;
    private final MutableLiveData<Void> createSuccess;
    private final ApiInterface api;

    public AppointmentViewModel() {
        createInvalid = new MutableLiveData<>();
        createError = new MutableLiveData<>();
        createSuccess = new MutableLiveData<>();
        api = ApiClient.getClient().create(ApiInterface.class);
    }

    public void validate(String typeAppointment, String typeProcedure, String date, String clinic) {
        if (date.isEmpty()) {
            createInvalid.setValue(null);
            return;
        }

        final Appointment appointment = new Appointment();
        appointment.setTypeAppointment(typeAppointment);
        appointment.setTypeProcedure(typeProcedure);
        appointment.setDate(date);
        appointment.setClinic(clinic);

        callApi(appointment);
    }

    private void callApi(Appointment appointment) {
        final Call<SuccessMessage> call = api.createAppointment(appointment);
        call.enqueue(new Callback<SuccessMessage>() {
            @Override
            public void onResponse(Call<SuccessMessage> call, Response<SuccessMessage> response) {
                Log.d("LKSDLSKDLSKD", "LSDLSKLKDLKD SUCESSS ");
                createSuccess.setValue(null);
            }

            @Override
            public void onFailure(@NonNull Call<SuccessMessage> call, Throwable t) {
                Log.d("LKSDLSKDLSKD", "LSDLSKLKDLKD t " + t.getMessage());
                createError.setValue(t.getMessage());
            }
        });
    }

    public MutableLiveData<Void> getCreateInvalid() {
        return createInvalid;
    }

    public MutableLiveData<String> getCreateError() {
        return createError;
    }

    public MutableLiveData<Void> getCreateSuccess() {
        return createSuccess;
    }
}