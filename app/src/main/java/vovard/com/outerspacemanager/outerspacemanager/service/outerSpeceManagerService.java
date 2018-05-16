package vovard.com.outerspacemanager.outerspacemanager.service;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import vovard.com.outerspacemanager.outerspacemanager.apiResponse.AuthentificationResponce;
import vovard.com.outerspacemanager.outerspacemanager.apiResponse.BuildingResponse;
import vovard.com.outerspacemanager.outerspacemanager.apiResponse.CreateBuildingResponce;
import vovard.com.outerspacemanager.outerspacemanager.apiResponse.CreateShipResponce;
import vovard.com.outerspacemanager.outerspacemanager.apiResponse.CurrentUserResponce;
import vovard.com.outerspacemanager.outerspacemanager.apiResponse.FleetResponce;
import vovard.com.outerspacemanager.outerspacemanager.apiResponse.SearchResponce;
import vovard.com.outerspacemanager.outerspacemanager.apiResponse.ShipResponce;
import vovard.com.outerspacemanager.outerspacemanager.apiResponse.UsersResponce;
import vovard.com.outerspacemanager.outerspacemanager.entity.UserClass;


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
