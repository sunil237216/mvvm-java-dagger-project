package com.example.test.mvvmsampleapp.service.network;

import com.example.test.mvvmsampleapp.service.model.LoginResponse;
import com.example.test.mvvmsampleapp.service.model.Phase3Response;
import com.example.test.mvvmsampleapp.service.model.StatusResponse;
import com.example.test.mvvmsampleapp.service.model.SummaryRequest;
import com.example.test.mvvmsampleapp.service.model.SummaryResponse;
import com.example.test.mvvmsampleapp.service.model.TsnRequest;
import com.example.test.mvvmsampleapp.service.model.TsnResponse;
import com.example.test.mvvmsampleapp.service.model.UserLogin;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sunil.jadhav on 11/12/2018.
 */

public interface ApiService {
    String HTTP_API_LOGIN_URL="http://13.71.2.75/RFD/api/";

    @GET("Phase1/Get")
    Observable<TsnRequest> getTsn(@Header("Authorization") String sessionIdAndToken, @Query("TSN") String number);

    @GET("Phase2/Get")
    Observable<TsnResponse> getTsn2(@Header("Authorization") String sessionIdAndToken,@Query("TSN") String number,@Query("ModelNumber") String ModelNumber);

    @GET("Phase3/Get")
    Observable<Phase3Response> getTsn3(@Header("Authorization") String sessionIdAndToken,@Query("TSN") String number, @Query("ModelNumber") String ModelNumber);

    @Headers("Content-Type: application/json")
    @POST("Login/fetchtoken")
    Observable<LoginResponse> login(@Body UserLogin body);

    @Headers("Content-Type: application/json")
    @POST("Phase1/Upsert")
    Observable<ResponseBody> sendStatus(@Header("Authorization") String sessionIdAndToken, @Body TsnRequest body);

    @Headers("Content-Type: application/json")
    @POST("Phase3/Upsert")
    Observable<ResponseBody> sendListStatus(@Header("Authorization") String sessionIdAndToken,@Body StatusResponse body);


    @Headers("Content-Type: application/json")
    @POST("Phase2/Upsert")
    Observable<ResponseBody> sendStatus2(@Header("Authorization") String sessionIdAndToken,@Body TsnRequest body);

    @Headers("Content-Type: application/json")
    @POST("Summary/GetSummary")
    Observable<SummaryResponse> getSummary(@Header("Authorization") String sessionIdAndToken,@Body SummaryRequest body);

    @Headers("Content-Type: application/json")
    @POST("Summary/DownloadReport")
    Observable<ResponseBody> sendReport(@Header("Authorization") String sessionIdAndToken,@Body SummaryRequest request);


    @GET("login/logout")
    Observable<ResponseBody> logout(@Header("Authorization") String sessionIdAndToken);


}
