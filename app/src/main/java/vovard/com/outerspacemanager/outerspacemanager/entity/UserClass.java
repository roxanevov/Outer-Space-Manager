package vovard.com.outerspacemanager.outerspacemanager.entity;


public class UserClass {
    private String email;
    private String username;
    private String password;



    private String points;
    private String mineralsModifier;
    private String minerals;
    private String gasModifier;
    private String gas;



    private String imageUrl;
    public UserClass(String username, String password){
        this.username = username;
        this.password = password;
    }

    public UserClass(String username, String password, String email) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.points = "";
        this.mineralsModifier = "";
        this.minerals = "";
        this.gasModifier = "";
        this.gas = "";

    }

    public UserClass(String gas, String gasModifier, String minerals, String mineralsModifier, String points) {

        this.points = points;
        this.mineralsModifier = mineralsModifier;
        this.minerals = minerals;
        this.gasModifier = gasModifier;
        this.gas = gas;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getMineralsModifier() {
        return mineralsModifier;
    }

    public void setMineralsModifier(String mineralsModifier) {
        this.mineralsModifier = mineralsModifier;
    }

    public String getMinerals() {
        return minerals;
    }

    public void setMinerals(String minerals) {
        this.minerals = minerals;
    }

    public String getGasModifier() {
        return gasModifier;
    }

    public void setGasModifier(String gasModifier) {
        this.gasModifier = gasModifier;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getImageUrl() { return imageUrl; }

}
