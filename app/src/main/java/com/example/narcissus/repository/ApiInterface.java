package com.example.narcissus.repository;

import com.example.narcissus.data.SuccessMessage;
import com.example.narcissus.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/signup")
    Call<SuccessMessage> signup(@Body User user);

    @POST("/login")
    Call<SuccessMessage> login(@Body User user);
}
