package vovard.com.outerspacemanager.outerspacemanager.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vovard.com.outerspacemanager.outerspacemanager.apiResponse.UsersResponce;
import vovard.com.outerspacemanager.outerspacemanager.Constant;
import vovard.com.outerspacemanager.outerspacemanager.entity.UserClass;
import vovard.com.outerspacemanager.outerspacemanager.viewAdapter.GalaxieListeViewAdapter;
import vovard.com.outerspacemanager.outerspacemanager.R;
import vovard.com.outerspacemanager.outerspacemanager.service.outerSpeceManagerService;

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

                if (response.code() != 200) {
                    try {
                        JSONObject jsonError = new JSONObject(response.errorBody().string());
                        Toast.makeText( getApplicationContext(), jsonError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {

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
