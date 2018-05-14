package vovard.com.outerspacemanager.outerspacemanager.Fragment.Building;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.BuildingResponse;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.CreateBuildingResponce;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.CreateShipResponce;
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
    Button BuildingConstruirButton;
    ImageView BuildingImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences isToken = getActivity().getSharedPreferences(TOKEN, 0);
        token = isToken.getString("token", null);

        View v = inflater.inflate(R.layout.fragment_building_detail,container);

        BuildingNameTextView = v.findViewById(R.id.BuildingNameTextView);
        BuildingImage = v.findViewById(R.id.BuildingImage);
        BuildingEffectTextView = v.findViewById(R.id.BuildingEffectTextView);
        BuildingBuildingTextView = v.findViewById(R.id.BuildingBuildingTextView);
        BuildingLevelTextView = v.findViewById(R.id.BuildingLevelTextView);
        BuildingConstruirButton = v.findViewById(R.id.buttonConstruirBuilding);

        BuildingConstruirButton.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    construire(building.getBuildingId());
                }
            }
        );
        return v;
    }

    public void replaceBuilding(final String buildingId) {

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

    private void construire( String idBuilding){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BDD)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);
        Call<CreateBuildingResponce> request = service.createBuilding(token, Long.parseLong(idBuilding));

        request.enqueue(new Callback<CreateBuildingResponce>() {
            @Override
            public void onResponse(Call<CreateBuildingResponce> call, Response<CreateBuildingResponce> response) {
                if (response.code() != 200) {
                    try {
                        JSONObject jsonError = new JSONObject(response.errorBody().string());
                        Toast.makeText( getContext(), jsonError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText( getContext(), "En construction", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CreateBuildingResponce> call, Throwable t) {
                Toast.makeText( getContext(), "Error", Toast.LENGTH_SHORT).show();
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

        Glide.with(getContext())
                .load(building.getImageUrl())
                .into(BuildingImage);
    }


}
