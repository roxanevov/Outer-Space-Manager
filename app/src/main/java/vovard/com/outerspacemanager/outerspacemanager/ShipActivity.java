package vovard.com.outerspacemanager.outerspacemanager;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShipActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static String TOKEN = "";
    private List<Ship> ships;
    public String token;
    private ListView ListViewShip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ship);
        ListViewShip = (ListView)findViewById(R.id.ListViewShip);
        ListViewShip.setOnItemClickListener(this);

        SharedPreferences isToken = getSharedPreferences(TOKEN, 0);
        token = isToken.getString("token", null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);
        Call<ShipResponce> request = service.getShips(token);

        request.enqueue(new Callback<ShipResponce>() {
            @Override
            public void onResponse(Call<ShipResponce> call, Response<ShipResponce> response) {
                if (response.code() != 200) {
                    try {
                        //Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        Log.i("erreur", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShipResponce rss = response.body();
                    ships = rss.getShips();
                    ListViewShip.setAdapter(new ShipListeViewAdapter(ShipActivity.this,ships));
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
                .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);
        HashMap<String, Long> data = new HashMap<>();
        data.put("shipId", id);
        data.put("amount", 1l);
        Call<CreateShipResponce> request = service.createShip(token,data);

        request.enqueue(new Callback<CreateShipResponce>() {
            @Override
            public void onResponse(Call<CreateShipResponce> call, Response<CreateShipResponce> response) {
                if (response.code() != 200) {
                    try {
                        Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        Log.i("erreur", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    CreateShipResponce rss = response.body();
                    Toast.makeText(getApplicationContext(), "En cpnstruction", Toast.LENGTH_SHORT).show();
                    Log.i("good", response.body().toString());
                }
            }
            @Override
            public void onFailure(Call<CreateShipResponce> call, Throwable t) {
            }
        });

        Toast.makeText(getApplicationContext(),"J'ai sélectionné l'item "+id,Toast.LENGTH_SHORT).show();
        Log.d("monApplication","J'ai sélectionné"+id);

    }
}
