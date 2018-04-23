package vovard.com.outerspacemanager.outerspacemanager.Fragment.Building;

import android.content.SharedPreferences;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.BuildingResponse;
import vovard.com.outerspacemanager.outerspacemanager.Constant;
import vovard.com.outerspacemanager.outerspacemanager.Entity.Building;
import vovard.com.outerspacemanager.outerspacemanager.R;
import vovard.com.outerspacemanager.outerspacemanager.Service.outerSpeceManagerService;

public class FragmentBuildingDetail extends Fragment {

    public static String TOKEN = "";
    public String token;
    public Building building;

    TextView BuildingNameTextView;
    TextView BuildingEffectTextView;
    TextView BuildingBuildingTextView;
    TextView BuildingLevelTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_building_detail,container);

        BuildingNameTextView = v.findViewById(R.id.BuildingNameTextView);
        BuildingEffectTextView = v.findViewById(R.id.BuildingEffectTextView);
        BuildingBuildingTextView = v.findViewById(R.id.BuildingBuildingTextView);
        BuildingLevelTextView = v.findViewById(R.id.BuildingLevelTextView);

        return v;
    }

    public void replaceBuilding(final String buildingId) {

        SharedPreferences isToken = getActivity().getSharedPreferences(TOKEN, 0);
        token = isToken.getString("token", null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BDD)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);
        Call<BuildingResponse> request = service.getBuilding(token);

        request.enqueue(new Callback<BuildingResponse>() {
            @Override
            public void onResponse(Call<BuildingResponse> call, Response<BuildingResponse> response) {
                building = response.body().getBuldings().get(Integer.parseInt(buildingId));
                setData(building);
            }

            @Override
            public void onFailure(Call<BuildingResponse> call, Throwable t) {

            }
        });
    }

    private void setData(Building building) {

        BuildingNameTextView.setText(building.getName());
        BuildingNameTextView.setVisibility(View.VISIBLE);

        BuildingEffectTextView.setText(building.getEffect());
        BuildingEffectTextView.setVisibility(View.VISIBLE);

        BuildingBuildingTextView.setText(building.getBuilding());
        BuildingBuildingTextView.setVisibility(View.VISIBLE);

        BuildingLevelTextView.setText(building.getLevel()+"");
        BuildingLevelTextView.setVisibility(View.VISIBLE);
    }
}
