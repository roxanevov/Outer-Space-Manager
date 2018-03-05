package vovard.com.outerspacemanager.outerspacemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        textViewPoint = (TextView) findViewById(R.id.textViewPoint);
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
                                .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);
                        Call<CurrentUserResponce> request = service.getCurrentuser(token);

                        request.enqueue(new Callback<CurrentUserResponce>() {
                            @Override
                            public void onResponse(Call<CurrentUserResponce> call, Response<CurrentUserResponce> response) {
                                if (response.code() != 200) {
                                    try {
                                        //Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                                        Log.i("erreur", response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    CurrentUserResponce rss = response.body();
                                    //Log.i("coucou", String.valueOf(rss.getPoints()));
                                    CurrentUserDatabase database = new CurrentUserDatabase(getApplicationContext());
                                    database.open();
                                    Cursor cursor = database.getUsername(rss.getUsername());
                                    Log.i("coucou", String.valueOf(cursor.getCount()));
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
    }
}
