package com.example.test.mvvmsampleapp.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.test.mvvmsampleapp.data.MyPreferenceManager;
import com.example.test.mvvmsampleapp.service.Repository;
import com.example.test.mvvmsampleapp.service.network.ApiClient;
import com.example.test.mvvmsampleapp.service.network.ApiService;
import com.example.test.mvvmsampleapp.viewmodel.ViewModelModule;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {ViewModelModule.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }


    @Provides
    @Singleton
    MyPreferenceManager getMyPreferenceManager(Application context){
        return new MyPreferenceManager(context);
    }


//    @Provides
//    @Singleton
//    Retrofit provideRetrofit()
//    {
//        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        clientBuilder.addInterceptor(loggingInterceptor);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ApiService.HTTP_API_LOGIN_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(clientBuilder.build())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        return retrofit;
//    }
//
//    @Singleton
//    @Provides
//    ApiService provideApiService(Retrofit  retrofit) {
//        return retrofit.create(ApiService.class);
//    }
}
