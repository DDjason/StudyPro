package com.example.jason.studypro.viewCtrl;

import android.util.Log;
import android.view.View;

import com.example.jason.network.HttpResponse;
import com.example.jason.studypro.MyService;
import com.example.jason.studypro.databinding.PswShowActBinding;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/18$ 14:25$
 * <p/>
 */
public class PswTextCtrl {
    private String            psw;
    private PswShowActBinding binding;
    Retrofit retrofit;

    public PswTextCtrl(PswShowActBinding binding) {
        this.binding = binding;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog", "retrofitBack = " + message);
            }
        });
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(2000, TimeUnit.SECONDS)
                .readTimeout(2000, TimeUnit.SECONDS)
                .writeTimeout(2000, TimeUnit.SECONDS)
                .build();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://" + "120.77.84.254:8810")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void onClickPsw(View view) {
        Log.i(this.getClass().getName(), binding.pswText.getPsw());
        Call<HttpResponse> call = retrofit.create(MyService.class).groupList(binding.pswText.getPsw());
        Log.i("Service Proxy", new Gson().toJson(retrofit.create(MyService.class).getClass().getSimpleName()));
        call.enqueue(new Callback<HttpResponse>() {
            @Override
            public void onResponse(Call<HttpResponse> call, Response<HttpResponse> response) {
                Log.i("onResponse", response.body().getRequest() + ":::answer");
            }

            @Override
            public void onFailure(Call<HttpResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
