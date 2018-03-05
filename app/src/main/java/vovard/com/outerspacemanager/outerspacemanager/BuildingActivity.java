package vovard.com.outerspacemanager.outerspacemanager;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static vovard.com.outerspacemanager.outerspacemanager.SingUpActivity.TOKEN;

public class BuildingActivity extends Activity implements AdapterView.OnItemClickListener {
    public static String TOKEN = "";
    private List<Building> buildings;
    public String token;
    private ListView ListViewBuilding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_building);
        ListViewBuilding = (ListView)findViewById(R.id.ListViewBuilding);
        ListViewBuilding.setOnItemClickListener(this);

        SharedPreferences isToken = getSharedPreferences(TOKEN, 0);
        token = isToken.getString("token", null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);
        Call<BuildingResponce> request = service.getBuilding(token);

        request.enqueue(new Callback<BuildingResponce>() {
            @Override
            public void onResponse(Call<BuildingResponce> call, Response<BuildingResponce> response) {
                if (response.code() != 200) {
                    try {
                        //Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        Log.i("erreur", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    BuildingResponce rss = response.body();
                    buildings = rss.getBuldings();
                    ListViewBuilding.setAdapter(new BuildingListeViewAdapter(BuildingActivity.this,buildings));
                }

            }

            @Override
            public void onFailure(Call<BuildingResponce> call, Throwable t) {

            }
        });

       /* ListViewBuilding = (ListView)findViewById(R.id.ListViewBuilding);

        ListViewBuilding.setAdapter(new BuildingListeViewAdapter(getApplicationContext(),buildings));
        ListViewBuilding.setOnItemClickListener(this);*/
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);
        Call<CreateBuildingResponce> request = service.createBuilding(token, id);

        request.enqueue(new Callback<CreateBuildingResponce>() {
            @Override
            public void onResponse(Call<CreateBuildingResponce> call, Response<CreateBuildingResponce> response) {
                if (response.code() != 200) {
                    try {
                        Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        //Log.i("erreur", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    CreateBuildingResponce rss = response.body();


                }

            }
            @Override
            public void onFailure(Call<CreateBuildingResponce> call, Throwable t) {

            }
        });

            Toast.makeText(getApplicationContext(),"J'ai sélectionné l'item "+id,Toast.LENGTH_SHORT).show();
            Log.d("monApplication","J'ai sélectionné"+id);

    }
}
