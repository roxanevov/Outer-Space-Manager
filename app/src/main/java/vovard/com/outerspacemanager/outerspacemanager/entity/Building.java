package vovard.com.outerspacemanager.outerspacemanager.entity;

/**
 * Created by rvovard on 27/02/2018.
 */

public class Building {

    private String level;
    private String amountOfEffectByLevel;
    private String amountOfEffectLevel0;
    private String buildingId;
    private String building;
    private String effect;
    private String gasCostByLevel;
    private String gasCostLevel0;
    private String imageUrl;
    private String mineralCostByLevel;
    private String mineralCostLevel0;
    private String name;
    private String timeToBuildByLevel;
    private String timeToBuildLevel0;

    public Building(String level, String amountOfEffectByLevel, String amountOfEffectLevel0, String buildingId, String building, String effect,String gasCostByLevel, String gasCostLevel0, String imageUrl,String mineralCostByLevel, String mineralCostLevel0, String name,String timeToBuildByLevel, String timeToBuildLevel0) {
        this.level = level;
        this. amountOfEffectByLevel = amountOfEffectByLevel;
        this. amountOfEffectLevel0 = amountOfEffectLevel0;
        this. buildingId = buildingId;
        this. building = building;
        this. effect = effect;
        this. gasCostByLevel = gasCostByLevel;
        this. gasCostLevel0 = gasCostLevel0;
        this. imageUrl = imageUrl;
        this. mineralCostByLevel = mineralCostByLevel;
        this. mineralCostLevel0 = mineralCostLevel0;
        this. name = name;
        this. timeToBuildByLevel = timeToBuildByLevel;
        this. timeToBuildLevel0 = timeToBuildLevel0;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAmountOfEffectByLevel() {
        return amountOfEffectByLevel;
    }

    public void setAmountOfEffectByLevel(String amountOfEffectByLevel) {
        this.amountOfEffectByLevel = amountOfEffectByLevel;
    }

    public String getAmountOfEffectLevel0() {
        return amountOfEffectLevel0;
    }

    public void setAmountOfEffectLevel0(String amountOfEffectLevel0) {
        this.amountOfEffectLevel0 = amountOfEffectLevel0;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getGasCostByLevel() {
        return gasCostByLevel;
    }

    public void setGasCostByLevel(String gasCostByLevel) {
        this.gasCostByLevel = gasCostByLevel;
    }

    public String getGasCostLevel0() {
        return gasCostLevel0;
    }

    public void setGasCostLevel0(String gasCostLevel0) {
        this.gasCostLevel0 = gasCostLevel0;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMineralCostByLevel() {
        return mineralCostByLevel;
    }

    public void setMineralCostByLevel(String mineralCostByLevel) {
        this.mineralCostByLevel = mineralCostByLevel;
    }

    public String getMineralCostLevel0() {
        return mineralCostLevel0;
    }

    public void setMineralCostLevel0(String mineralCostLevel0) {
        this.mineralCostLevel0 = mineralCostLevel0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeToBuildByLevel() {
        return timeToBuildByLevel;
    }

    public void setTimeToBuildByLevel(String timeToBuildByLevel) {
        this.timeToBuildByLevel = timeToBuildByLevel;
    }

    public String getTimeToBuildLevel0() {
        return timeToBuildLevel0;
    }

    public void setTimeToBuildLevel0(String timeToBuildLevel0) {
        this.timeToBuildLevel0 = timeToBuildLevel0;
    }

}
