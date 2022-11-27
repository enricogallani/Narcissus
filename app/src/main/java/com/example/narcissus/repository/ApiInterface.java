package com.example.narcissus.repository;

import com.example.narcissus.data.Appointment;
import com.example.narcissus.data.Message;
import com.example.narcissus.data.SuccessMessage;
import com.example.narcissus.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/signup")
    Call<SuccessMessage> signup(@Body User user);

    @POST("/login")
    Call<User> login(@Body User user);

    @POST("/appointment")
    Call<SuccessMessage> createAppointment(@Body Appointment appointment);

    @POST("/message")
    Call<SuccessMessage> createMessage(@Body Message message);

}
