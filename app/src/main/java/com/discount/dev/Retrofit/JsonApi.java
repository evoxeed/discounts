package com.discount.dev.Retrofit;

import com.discount.dev.Retrofit.Models.Authorization;
import com.discount.dev.Retrofit.Models.Categories;
import com.discount.dev.Retrofit.Models.Cities;
import com.discount.dev.Retrofit.Models.Partners;
import com.discount.dev.Retrofit.Models.RefreshToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface JsonApi {
    @GET("/api/partners")
    Call<List<Partners>> getPartners();

    @GET("/api/categories")
    Call<List<Categories>> getCategories();

    @GET("/api/cities")
    Call<List<Cities>> getCities();

    @FormUrlEncoded
    @POST("/api/login")
    Call<Authorization> auth(@Field("email") String login, @Field("password") String password);

    @POST("/api/refresh")
    Call<RefreshToken> refreshToken(@Header("Authorization") String token);
}
