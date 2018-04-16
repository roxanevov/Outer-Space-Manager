package vovard.com.outerspacemanager.outerspacemanager.APIResponse;

/**
 * Created by rvovard on 23/01/2018.
 */

public class CurrentUserResponce {

    public String gas;
    public String gasModifier;
    public String minerals;
    public String mineralsModifier;
    public String points;
    private String username;

    public CurrentUserResponce(String username, String password, String email, String points, String mineralsModifier, String minerals, String gasModifier, String gas){
        this.username = username;
        this.points = points;
        this.mineralsModifier = mineralsModifier;
        this.minerals = minerals;
        this.gasModifier = gasModifier;
        this.gas = gas;
    }
    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getGasModifier() {
        return gasModifier;
    }

    public void setGasModifier(String gasModifier) {
        this.gasModifier = gasModifier;
    }

    public String getMinerals() {
        return minerals;
    }

    public void setMinerals(String minerals) {
        this.minerals = minerals;
    }

    public String getMineralsModifier() {
        return mineralsModifier;
    }

    public void setMineralsModifier(String mineralsModifier) {
        this.mineralsModifier = mineralsModifier;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
