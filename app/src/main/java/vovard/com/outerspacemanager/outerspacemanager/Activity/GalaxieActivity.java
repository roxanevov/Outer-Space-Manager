package vovard.com.outerspacemanager.outerspacemanager.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.UsersResponce;
import vovard.com.outerspacemanager.outerspacemanager.Constant;
import vovard.com.outerspacemanager.outerspacemanager.Entity.UserClass;
import vovard.com.outerspacemanager.outerspacemanager.ViewAdapter.GalaxieListeViewAdapter;
import vovard.com.outerspacemanager.outerspacemanager.R;
import vovard.com.outerspacemanager.outerspacemanager.Service.outerSpeceManagerService;

public class GalaxieActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static String TOKEN = "";
    private List<UserClass> user;
    public String token;
    private ListView ListeViewGalaxie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_galaxie);
        ListeViewGalaxie = (ListView) findViewById(R.id.ListViewGalaxie);
        ListeViewGalaxie.setOnItemClickListener(this);

        SharedPreferences isToken = getSharedPreferences(TOKEN, 0);
        token = isToken.getString("token", null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BDD)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);
        Call<UsersResponce> request = service.getUsers(token);

        request.enqueue(new Callback<UsersResponce>() {
            @Override
            public void onResponse(Call<UsersResponce> call, Response<UsersResponce> response) {
                Log.i("coucou", response.toString());
                if (response.code() != 200) {
                    try {
                        //Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        Log.i("erreur", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("responce", response.body().toString());
                    UsersResponce rss = response.body();
                    user = rss.getUser();
                    ListeViewGalaxie.setAdapter(new GalaxieListeViewAdapter(GalaxieActivity.this, user));
                }

            }

            @Override
            public void onFailure(Call<UsersResponce> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
