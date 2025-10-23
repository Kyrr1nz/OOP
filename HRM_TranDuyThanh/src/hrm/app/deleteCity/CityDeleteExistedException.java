package hrm.app.deleteCity;

public class CityDeleteExistedException  extends Exception{
    private final String cityId;
    public CityDeleteExistedException (String cityId) {
        super ("EmployeeId " + cityId + "not found");
        this.cityId = cityId;
    }
    public String CityId() {
        return cityId;
    }
}
