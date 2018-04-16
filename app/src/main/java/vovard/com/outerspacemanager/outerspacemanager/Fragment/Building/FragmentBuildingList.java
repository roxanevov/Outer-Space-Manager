package vovard.com.outerspacemanager.outerspacemanager.Fragment.Building;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.BuildingResponse;
import vovard.com.outerspacemanager.outerspacemanager.Activity.BuildingActivity;
import vovard.com.outerspacemanager.outerspacemanager.Constant;
import vovard.com.outerspacemanager.outerspacemanager.Entity.Building;
import vovard.com.outerspacemanager.outerspacemanager.R;
import vovard.com.outerspacemanager.outerspacemanager.Service.outerSpeceManagerService;
import vovard.com.outerspacemanager.outerspacemanager.ViewAdapter.BuildingListeViewAdapter;

public class FragmentBuildingList extends Fragment {
    private ListView ListViewBuilding;
    public static String TOKEN = "";
    public List<Building> buildings;
    public String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_building_liste,container);
        ListViewBuilding = v.findViewById(R.id.ListViewBuilding);
        return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
                if (response.code() != 200) {
                    try {
                        //Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        Log.i("erreur", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    BuildingResponse rss = response.body();
                    buildings = rss.getBuldings();
                    ListViewBuilding.setAdapter(new BuildingListeViewAdapter(getActivity(), buildings));
                    ListViewBuilding.setOnItemClickListener((BuildingActivity)getActivity());
                }

            }

            @Override
            public void onFailure(Call<BuildingResponse> call, Throwable t) {

            }
        });

    }
}
