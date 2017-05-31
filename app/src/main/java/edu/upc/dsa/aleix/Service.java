package edu.upc.dsa.aleix;


import android.widget.ImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface Service {

    @GET("/users/{name}/followers")
    Call<List<Follower>> getList(@Path("name") String name);

    @GET("/users/{name}/followers")
    Call<Follower> getPhoto(@Path("name") String name);
}
