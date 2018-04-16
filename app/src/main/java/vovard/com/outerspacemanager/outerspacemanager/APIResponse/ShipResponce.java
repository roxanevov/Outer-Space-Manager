package vovard.com.outerspacemanager.outerspacemanager.APIResponse;

import java.util.List;

import vovard.com.outerspacemanager.outerspacemanager.Entity.Ship;

/**
 * Created by rvovard on 13/03/2018.
 */

public class ShipResponce {

    private Double currentUserMinerals;
    private Double currentUserGas;
    private Integer size;
    private List<Ship> ships ;

    public Double getCurrentUserMinerals() {
        return currentUserMinerals;
    }

    public Double getCurrentUserGas() {
        return currentUserGas;
    }

    public Integer getSize() {
        return size;
    }

    public List<Ship> getShips() {
        return ships;
    }

}
