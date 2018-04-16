package vovard.com.outerspacemanager.outerspacemanager.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import vovard.com.outerspacemanager.outerspacemanager.Constant;
import vovard.com.outerspacemanager.outerspacemanager.Fragment.Building.FragmentBuildingDetail;
import vovard.com.outerspacemanager.outerspacemanager.R;

public class BuildingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);
        FragmentBuildingDetail fragB = (FragmentBuildingDetail)getSupportFragmentManager().findFragmentById(R.id.fragmentB);
        Log.i("fragb", String.valueOf(fragB));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentBuildingDetail fragB = (FragmentBuildingDetail)getSupportFragmentManager().findFragmentById(R.id.fragmentB);

        if(fragB == null || !fragB.isInLayout()){
            Intent i = new Intent(getApplicationContext(), BuildingDetailActivity.class);
            i.putExtra(Constant.EXTRA_BUILDING_ID, position);
            startActivity(i);
        } else {
             fragB.replaceBuilding(String.valueOf(position));
        }
    }
}
