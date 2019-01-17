package com.example.test.mvvmsampleapp.service.network;

import com.example.test.mvvmsampleapp.service.Repository;
import com.example.test.mvvmsampleapp.service.model.LoginResponse;
import com.example.test.mvvmsampleapp.service.model.TsnRequest;
import com.example.test.mvvmsampleapp.service.model.TsnResponse;
import com.example.test.mvvmsampleapp.service.model.UserLogin;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Inject;

import io.reactivex.Observable;

import io.reactivex.ObservableEmitter;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient implements Repository {

private ApiService apiService;

    @Inject
    public ApiClient() {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.HTTP_API_LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }

    public Observable<TsnRequest> getTsn(String TSNumber,String token) {

        return apiService.getTsn(token,TSNumber);
    }
    public Observable<TsnResponse> getTsn2(String token,String TSNumber,String modelid) {

        return apiService.getTsn2(token,TSNumber,modelid);
    }

    public Observable<LoginResponse> login(String userID, String pass) {
        return apiService.login(new UserLogin(userID, pass));
    }

    public Observable<ResponseBody> sendStatus(TsnRequest request, String token) {

        return apiService.sendStatus(token,request);
    }

    public Observable<ResponseBody> sendStatus2(TsnRequest request,String token) {

        return apiService.sendStatus2(token,request);
    }


    public Observable<ResponseBody> logout(String sessionIdAndToken)
    {
        return  apiService.logout(sessionIdAndToken);
    }




}
