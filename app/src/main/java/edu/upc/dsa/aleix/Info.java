package edu.upc.dsa.aleix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Info extends AppCompatActivity {

    private final String BASE_URL= "https://api.github.com"; //URL
    private List<Follower> listaFollow;
    private List<String> listaNombres;
    private ListView listv;
    private ImageView imagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        try {
            Bundle extra = getIntent().getExtras();
            String name = extra.getString("name");

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
//
            Retrofit retrofit =
                    builder.client(httpClient.build()).build();

            // Create an instance of our GitHub API interface.
            Service getList = retrofit.create(Service.class);

            // Create a call instance for looking up Retrofit contributors.
            Call<List<Follower>> call = getList.getList(name);

            // Fetch and print a list of the contributors to the library.
            call.enqueue(new Callback<List<Follower>>() {

                //***************Comprobacion de que recoge los datos**********
                @Override
                public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                    if(response.code()==200){
                        listaFollow=(List<Follower>) response.body();
                        listv = (ListView) findViewById(R.id.listaV);
                        listaNombres = new ArrayList<>();
                        for (int j=0; j < listaFollow.size(); j++){
                            String item = listaFollow.get(j).getLogin();
                            listaNombres.add (item);
                        }
                        ArrayAdapter<String> arrayAdapter =  new ArrayAdapter<String>
                                (Info.this, android.R.layout.simple_list_item_1, listaNombres);
                        listv.setAdapter(arrayAdapter);
                    }
                    else {
                        Toast.makeText(Info.this, "No funciona: "+response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Follower>> call, Throwable t) {
                    Toast.makeText(Info.this, "No funciona", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e){
            Toast.makeText(Info.this, "No funciona", Toast.LENGTH_SHORT).show();
        }

        try {
            Bundle extra = getIntent().getExtras();
            String name = extra.getString("name");

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
//
            Retrofit retrofit =
                    builder.client(httpClient.build()).build();

            // Create an instance of our GitHub API interface.
            Service getList = retrofit.create(Service.class);

            // Create a call instance for looking up Retrofit contributors.
            Call<ImageView> call = getList.getPhoto(name);

            // Fetch and print a list of the contributors to the library.
            call.enqueue(new Callback<ImageView>() {

                //***************Comprobacion de que recoge los datos**********
                @Override
                public void onResponse(Call<ImageView> call, Response<ImageView> response) {
                    if(response.code()==200){
                        imagen=(ImageView) response.body();
                        
                    }
                    else {
                        Toast.makeText(Info.this, "No funciona: "+response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ImageView> call, Throwable t) {
                    Toast.makeText(Info.this, "No funciona", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e){
            Toast.makeText(Info.this, "No funciona", Toast.LENGTH_SHORT).show();
        }
    }

}
