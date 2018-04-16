package vovard.com.outerspacemanager.outerspacemanager.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.AuthentificationResponce;
import vovard.com.outerspacemanager.outerspacemanager.Constant;
import vovard.com.outerspacemanager.outerspacemanager.Entity.UserClass;
import vovard.com.outerspacemanager.outerspacemanager.R;
import vovard.com.outerspacemanager.outerspacemanager.Service.outerSpeceManagerService;

public class SingUpActivity extends AppCompatActivity {

    public EditText editTextLogin;
    public EditText editTextPassword;
    public Button buttonValider;
    public Button buttonLogin;

    public String login;
    public String password;
    public String email;

    public UserClass user;

    public static String TOKEN = "";

    public  String theToken;


   //public Context context = getApplicationContext();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonValider = (Button) findViewById(R.id.buttonValider);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);

        SharedPreferences isToken = getSharedPreferences(TOKEN, 0);
        theToken = isToken.getString("token", null);
        //on change de vue
        if(theToken != null){
            Intent intent = new Intent(SingUpActivity.this, MainActivity.class);
            startActivity(intent);
        }

        buttonValider.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login = editTextLogin.getText().toString();
                    password = editTextPassword.getText().toString();
                    email = "roxane3578524@gmail.com";
                    user = new UserClass(login, password, email);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constant.URL_BDD)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);
                    Call<AuthentificationResponce> request = service.user(user);

                    request.enqueue(new Callback<AuthentificationResponce>() {
                        @Override
                        public void onResponse(Call<AuthentificationResponce> call, Response<AuthentificationResponce> response) {
                            if (response.code() != 200) {
                                try {
                                    Toast.makeText(getApplicationContext(),  response.errorBody().string(), Toast.LENGTH_SHORT).show();
                                    Log.i("erreur", response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }else{
                                AuthentificationResponce rss = response.body();
                                Log.i("coucou",rss.getToken());
                                SharedPreferences token = getSharedPreferences(TOKEN, 0);
                                SharedPreferences.Editor editor = token.edit();
                                editor.putString("token", rss.getToken());
                                editor.putString("login", login);
                                editor.apply();
                                editor.commit();

                                Intent intent = new Intent(SingUpActivity.this, MainActivity.class);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {

                        }
                    });

                }
            }
        );
        buttonLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        login = editTextLogin.getText().toString();
                        password = editTextPassword.getText().toString();
                        user = new UserClass(login, password);

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(Constant.URL_BDD)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);
                        Call<AuthentificationResponce> request = service.userLogin(user);

                        request.enqueue(new Callback<AuthentificationResponce>() {
                            @Override
                            public void onResponse(Call<AuthentificationResponce> call, Response<AuthentificationResponce> response) {
                                if (response.code() != 200) {
                                    try {
                                        Toast.makeText(getApplicationContext(),  response.errorBody().string(), Toast.LENGTH_SHORT).show();
                                        Log.i("erreur", response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    AuthentificationResponce rss = response.body();
                                    Log.i("coucou",rss.getToken());
                                    SharedPreferences token = getSharedPreferences(TOKEN, 0);
                                    SharedPreferences.Editor editor = token.edit();
                                    editor.putString("token", rss.getToken());
                                    editor.putString("login", login);
                                    editor.apply();
                                    editor.commit();

                                    Intent intent = new Intent(SingUpActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }

                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {

                            }
                        });

                    }
                }
        );

    }

}

