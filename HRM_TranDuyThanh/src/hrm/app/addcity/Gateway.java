package hrm.app.addcity;
import hrm.core.City;
public interface Gateway {
    void insertCity(City city)
            throws CityExistedException;
}
