package vovard.com.outerspacemanager.outerspacemanager.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vovard.com.outerspacemanager.outerspacemanager.apiResponse.CreateShipResponce;
import vovard.com.outerspacemanager.outerspacemanager.apiResponse.ShipResponce;
import vovard.com.outerspacemanager.outerspacemanager.Constant;
import vovard.com.outerspacemanager.outerspacemanager.entity.Ship;
import vovard.com.outerspacemanager.outerspacemanager.R;
import vovard.com.outerspacemanager.outerspacemanager.viewAdapter.ShipListeViewAdapter;
import vovard.com.outerspacemanager.outerspacemanager.service.outerSpeceManagerService;

public class ShipActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static String TOKEN = "";
    private List<Ship> ships;
    public String token;


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ship);
        mRecyclerView = (RecyclerView) findViewById(R.id.ListViewShip);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);



        SharedPreferences isToken = getSharedPreferences(TOKEN, 0);
        token = isToken.getString("token", null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BDD)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);
        Call<ShipResponce> request = service.getShips(token);

        request.enqueue(new Callback<ShipResponce>() {
            @Override
            public void onResponse(Call<ShipResponce> call, Response<ShipResponce> response) {
                if (response.code() != 200) {
                    try {
                        JSONObject jsonError = new JSONObject(response.errorBody().string());
                        Toast.makeText( getApplicationContext(), jsonError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    ShipResponce rss = response.body();
                    ships = rss.getShips();
                    mAdapter = new ShipListeViewAdapter(ShipActivity.this,ships);
                    mRecyclerView.setAdapter(mAdapter);

                }

            }

            @Override
            public void onFailure(Call<ShipResponce> call, Throwable t) {

            }
        });
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BDD)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("amount", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonParams.toString());
        Call<CreateShipResponce> request = service.createShip(token, id,body);

        request.enqueue(new Callback<CreateShipResponce>() {
            @Override
            public void onResponse(Call<CreateShipResponce> call, Response<CreateShipResponce> response) {
                if (response.code() != 200) {
                    try {
                        Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    CreateShipResponce rss = response.body();
                    Toast.makeText(getApplicationContext(), "En construction", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<CreateShipResponce> call, Throwable t) {
            }
        });

        Toast.makeText(getApplicationContext(),"J'ai sélectionné l'item "+id,Toast.LENGTH_SHORT).show();


    }
}
