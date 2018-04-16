package vovard.com.outerspacemanager.outerspacemanager.Service;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.AuthentificationResponce;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.BuildingResponse;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.CreateBuildingResponce;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.CreateShipResponce;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.CurrentUserResponce;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.FleetResponce;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.SearchResponce;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.ShipResponce;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.UsersResponce;
import vovard.com.outerspacemanager.outerspacemanager.Entity.UserClass;


public interface outerSpeceManagerService {
    @POST("auth/create")
    Call<AuthentificationResponce> user(@Body UserClass user);

    @POST("auth/login")
    Call<AuthentificationResponce> userLogin(@Body UserClass user);

    @GET("users/get")
    Call<CurrentUserResponce> getCurrentuser(@Header("x-access-token") String token);
    @GET("users/0/20")
    Call<UsersResponce> getUsers(@Header("x-access-token") String token);

    @GET("buildings/list")
    Call<BuildingResponse> getBuilding(@Header("x-access-token") String token);

    @POST("buildings/create/{buildingId}")
    Call<CreateBuildingResponce> createBuilding(@Header("x-access-token") String token, @Path("buildingId") long id);

    @GET("ships")
    Call<ShipResponce> getShips(@Header("x-access-token") String token);

    @POST("ships/create/{shipId}")
    Call<CreateShipResponce> createShip(@Header("x-access-token") String token, @Path("shipId") long id, @Body RequestBody data);

    @GET("fleet/list")
    Call<FleetResponce> listeFleet(@Header("x-access-token") String token);

    @GET("searches/list")
    Call<SearchResponce> listeSearches(@Header("x-access-token") String token);

}
