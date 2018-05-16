package vovard.com.outerspacemanager.outerspacemanager.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vovard.com.outerspacemanager.outerspacemanager.apiResponse.CurrentUserResponce;
import vovard.com.outerspacemanager.outerspacemanager.Constant;
import vovard.com.outerspacemanager.outerspacemanager.CurrentUserDatabase;
import vovard.com.outerspacemanager.outerspacemanager.entity.UserClass;
import vovard.com.outerspacemanager.outerspacemanager.R;
import vovard.com.outerspacemanager.outerspacemanager.service.outerSpeceManagerService;

public class MainActivity extends AppCompatActivity {
    public TextView textViewUserName;
    public TextView textViewPoint;
    public Button buttonVueGenerale;
    public Button buttonBatiment;
    public Button buttonFlotte;
    public Button buttonRecherches;
    public Button buttonChantirtSpatial;
    public Button buttonGalaxie;

    public String theLogin;
    public String token;

    public UserClass user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewUserName = (TextView) findViewById(R.id.textViewUsernameValue);
        buttonVueGenerale = (Button) findViewById(R.id.buttonVueGenerale);
        buttonBatiment = (Button) findViewById(R.id.buttonBatiment);
        buttonFlotte = (Button) findViewById(R.id.buttonFlotte);
        buttonRecherches = (Button) findViewById(R.id.buttonRecherches);
        buttonChantirtSpatial = (Button) findViewById(R.id.buttonChantirtSpatial);
        buttonGalaxie = (Button) findViewById(R.id.buttonGalaxie);

        SharedPreferences isLogin = getSharedPreferences(SingUpActivity.TOKEN, 0);
        theLogin = isLogin.getString("login", null);
        token = isLogin.getString("token", null);
        textViewUserName.setText(theLogin);

        buttonVueGenerale.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(Constant.URL_BDD)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);
                        Call<CurrentUserResponce> request = service.getCurrentuser(token);

                        request.enqueue(new Callback<CurrentUserResponce>() {
                            @Override
                            public void onResponse(Call<CurrentUserResponce> call, Response<CurrentUserResponce> response) {
                                if (response.code() != 200) {
                                    try {
                                        JSONObject jsonError = new JSONObject(response.errorBody().string());
                                        Toast.makeText( getApplicationContext(), jsonError.getString("message"), Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    CurrentUserResponce rss = response.body();

                                    CurrentUserDatabase database = new CurrentUserDatabase(getApplicationContext());
                                    database.open();
                                    Cursor cursor = database.getUsername(rss.getUsername());
                                    if(cursor.getCount() > 0){
                                        database.updateUser(rss.getGas(), rss.getGasModifier(), rss.getMinerals(), rss.getMineralsModifier(), rss.getPoints(), rss.getUsername());
                                    }else{
                                        database.insertUser(rss.getGas(), rss.getGasModifier(), rss.getMinerals(), rss.getMineralsModifier(), rss.getPoints(), rss.getUsername());
                                    }
                                    Gson gson = new Gson();
                                    UserClass user = database.getUser(rss.getUsername());

                                    Intent intent = new Intent(MainActivity.this, VueGeneraleActivity.class);
                                    intent.putExtra("username", rss.getUsername());
                                    intent.putExtra("user", gson.toJson(user));

                                    startActivity(intent);
                                    database.close();
                                }
                            }
                            @Override
                            public void onFailure(Call<CurrentUserResponce> call, Throwable t) {

                            }
                        });
                    }
                }
        );
        buttonBatiment.setOnClickListener(
             new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(MainActivity.this, BuildingActivity.class);
                     startActivity(intent);
                 }
             }
        );
        buttonChantirtSpatial.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,ShipActivity.class);
                        startActivity(intent);
                    }
                }
        );
        buttonFlotte.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,FleetActivity.class);
                        startActivity(intent);
                    }
                }
        );
        buttonGalaxie.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,GalaxieActivity.class);
                        startActivity(intent);
                    }
                }
        );

        buttonRecherches.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }
}
