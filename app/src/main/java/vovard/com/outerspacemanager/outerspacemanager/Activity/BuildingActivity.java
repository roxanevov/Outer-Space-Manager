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

public class BuildingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);
        FragmentBuildingDetail fragB = (FragmentBuildingDetail)getSupportFragmentManager().findFragmentById(R.id.fragmentB);
    }

    public void updateView(int position, boolean showFrag) {
        FragmentBuildingDetail fragB = (FragmentBuildingDetail)getSupportFragmentManager().findFragmentById(R.id.fragmentB);

        if((fragB == null || !fragB.isInLayout()) && !showFrag){
            Intent i = new Intent(getApplicationContext(), BuildingDetailActivity.class);
            i.putExtra(Constant.EXTRA_BUILDING_ID, position);
            startActivity(i);
        } else if(fragB != null && fragB.isInLayout()){
            fragB.replaceBuilding(String.valueOf(position));
        }
    }

}
