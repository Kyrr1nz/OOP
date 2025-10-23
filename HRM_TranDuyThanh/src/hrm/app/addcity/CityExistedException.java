package hrm.app.addcity;

public class CityExistedException extends Exception{
    private final String cityId;
    public CityExistedException(String cityId){
        this.cityId = cityId;
    }
    public String cityId(){
        return this.cityId;
    }
}
