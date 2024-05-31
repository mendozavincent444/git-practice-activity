public class Horse {
    private String name;
    private boolean healthy;
    private Integer distanceTraveled;
    private String warCry;

    public Horse(String name, boolean healthy, Integer distanceTraveled, String warCry) {
        this.name = name;
        this.healthy = healthy;
        this.distanceTraveled = distanceTraveled;
        this.warCry = warCry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }

    public Integer getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(Integer distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public String getWarCry() {
        return warCry;
    }

    public void setWarCry(String warCry) {
        this.warCry = warCry;
    }
}

