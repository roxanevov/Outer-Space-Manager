package vovard.com.outerspacemanager.outerspacemanager.Fragment.Building;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vovard.com.outerspacemanager.outerspacemanager.apiResponse.BuildingResponse;
import vovard.com.outerspacemanager.outerspacemanager.activity.BuildingActivity;
import vovard.com.outerspacemanager.outerspacemanager.Constant;
import vovard.com.outerspacemanager.outerspacemanager.entity.Building;
import vovard.com.outerspacemanager.outerspacemanager.R;
import vovard.com.outerspacemanager.outerspacemanager.service.outerSpeceManagerService;
import vovard.com.outerspacemanager.outerspacemanager.viewAdapter.BuildingListeViewAdapter;

public class FragmentBuildingList extends Fragment implements AdapterView.OnItemClickListener{
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
                        JSONObject jsonError = new JSONObject(response.errorBody().string());
                        Toast.makeText( getContext(), jsonError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    BuildingResponse rss = response.body();
                    buildings = rss.getBuldings();
                    ListViewBuilding.setAdapter(new BuildingListeViewAdapter(getActivity(), buildings));
                    ((BuildingActivity)getActivity()).updateView(0, true );
                }

            }

            @Override
            public void onFailure(Call<BuildingResponse> call, Throwable t) {

            }
        });

        ListViewBuilding.setOnItemClickListener(this);


    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((BuildingActivity)getActivity()).updateView(position, false);
    }
}
