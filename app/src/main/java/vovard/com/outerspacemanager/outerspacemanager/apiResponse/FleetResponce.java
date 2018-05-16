package vovard.com.outerspacemanager.outerspacemanager.apiResponse;

import java.util.List;

import vovard.com.outerspacemanager.outerspacemanager.entity.Ship;

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
