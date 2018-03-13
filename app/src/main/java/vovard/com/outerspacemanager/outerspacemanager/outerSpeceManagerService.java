package vovard.com.outerspacemanager.outerspacemanager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface outerSpeceManagerService {
    @POST("auth/create")
    Call<AuthentificationResponce> user(@Body UserClass user);

    @GET("users/get")
    Call<CurrentUserResponce> getCurrentuser(@Header("x-access-token") String token);

    @GET("buildings/list")
    Call<BuildingResponce> getBuilding(@Header("x-access-token") String token);

    @POST("buildings/create/{buildingId}")
    Call<CreateBuildingResponce> createBuilding(@Header("x-access-token") String token, @Path("buildingId") long id);

    @GET("ships")
    Call<ShipResponce> getShips(@Header("x-access-token") String token);

    @FormUrlEncoded
    @POST("ships/create/{shipId}")
    Call<CreateShipResponce> createShip(@Header("x-access-token") String token, @FieldMap HashMap<String, Long> data);

}
