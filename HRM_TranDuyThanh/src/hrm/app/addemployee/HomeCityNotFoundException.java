package hrm.app.addemployee;

public class HomeCityNotFoundException extends Exception {
    private final String cityId;

    public HomeCityNotFoundException(String cityId) {
        this.cityId = cityId;
    }
    public String cityId() {
        return this.cityId;
    }
}
