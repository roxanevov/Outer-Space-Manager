package vovard.com.outerspacemanager.outerspacemanager.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vovard.com.outerspacemanager.outerspacemanager.Constant;
import vovard.com.outerspacemanager.outerspacemanager.Fragment.Building.FragmentBuildingDetail;
import vovard.com.outerspacemanager.outerspacemanager.R;

public class BuildingDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail);

        FragmentBuildingDetail fragB = (FragmentBuildingDetail)getSupportFragmentManager().findFragmentById(R.id.fragmentB);
        fragB.replaceBuilding(String.valueOf(getIntent().getIntExtra(Constant.EXTRA_BUILDING_ID, 0)));
    }
}
