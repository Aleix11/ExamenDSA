package edu.upc.dsa.aleix;


import android.widget.ImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Mikel on 31/05/2017.
 */

interface Service {

    @GET("/users/{name}/followers")
    Call<List<Follower>> getList(@Path("name") String name);

    @GET("/users/{name}/followers")
    Call<ImageView> getPhoto(@Path("name") String name);
}
