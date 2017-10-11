package com.example.jason.studypro;

import com.example.jason.network.HttpResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/26$ 16:32$
 * <p/>
 */
public interface MyService {
    @GET("/get_result/{id}")
    Call<HttpResponse> groupList(@Path("id") String groupId);

    @GET("/get_result/{id}")
    Call<String> getResulr(@Path("id") String groupId);

    @GET("/my_method/invoke")
    Call<String> invokeUse();
}
