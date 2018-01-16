package vovard.com.outerspacemanager.outerspacemanager;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingUpActivity extends AppCompatActivity {

    public EditText editTextLogin;
    public EditText editTextPassword;
    public Button buttonValider;

    public String login;
    public String password;
    public String email;

    public UserClass user;

    public static final String TOKEN = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonValider = (Button) findViewById(R.id.buttonValider);

        buttonValider.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login = editTextLogin.getText().toString();
                    password = editTextPassword.getText().toString();
                    email = "ououfdgsdniofdgsrunuinui@gmail.com";
                    user = new UserClass(login, password, email);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);
                    Call<AuthentificationResponce> request = service.user(user);

                    request.enqueue(new Callback<AuthentificationResponce>() {
                        @Override
                        public void onResponse(Call<AuthentificationResponce> call, Response<AuthentificationResponce> response) {
                            AuthentificationResponce rss = response.body();
                            Log.i("coucou",rss.getToken());

                            if (response.code() != 200) {
                                try {
                                    Log.i("erreur", response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
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

