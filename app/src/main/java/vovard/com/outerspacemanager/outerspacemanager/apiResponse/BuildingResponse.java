package vovard.com.outerspacemanager.outerspacemanager.apiResponse;

import java.util.List;

import vovard.com.outerspacemanager.outerspacemanager.entity.Building;

public class BuildingResponse {
    private Integer size;
    private List<Building> buildings;

    public Integer getSize() {
        return size;
    }

    public List<Building> getBuldings() {
        return buildings;
    }


}
