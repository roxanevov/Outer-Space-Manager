package vovard.com.outerspacemanager.outerspacemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class VueGeneraleActivity extends AppCompatActivity {


    public TextView textViewGasValue;
    public TextView textViewGasModifieValue;
    public TextView textViewMineralsValue;
    public TextView textViewMineralsModifievalue;
    public TextView textViewPointValue;
    public TextView textViewUsernameValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_generale);



        textViewGasValue = (TextView) findViewById(R.id.textViewGasValue);
        textViewGasModifieValue = (TextView) findViewById(R.id.textViewGasModifieValue);
        textViewMineralsValue = (TextView) findViewById(R.id.textViewMineralsValue);
        textViewMineralsModifievalue = (TextView) findViewById(R.id.textViewMineralsModifievalue);
        textViewPointValue = (TextView) findViewById(R.id.textViewPointValue);
        textViewUsernameValue = (TextView) findViewById(R.id.textViewUsernameValue);

        Intent intent= getIntent();
        Gson gson = new Gson();

        String json = intent.getStringExtra("user");
        UserClass user = gson.fromJson(json,UserClass.class);

        textViewGasValue.setText(user.getGas());
        textViewGasModifieValue.setText(user.getGasModifier());
        textViewMineralsValue.setText(user.getMinerals());
        textViewMineralsModifievalue.setText(user.getMineralsModifier());
        textViewPointValue.setText(user.getPoints());
        textViewUsernameValue.setText(intent.getStringExtra("username"));
    }
}
