package vovard.com.outerspacemanager.outerspacemanager.APIResponse;

import java.util.List;

import vovard.com.outerspacemanager.outerspacemanager.Entity.Ship;

public class FleetResponce {

    private Integer size;
    private List<Ship> ships ;

    public Integer getSize() {
        return size;
    }
    public List<Ship> getShips() {
        return ships;
    }
}
