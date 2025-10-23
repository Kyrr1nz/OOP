package hrm.app.addcity;

import hrm.core.City;
import hrm.core.InvalidCityException;
public class UseCase {
    private final Gateway gateway;

    public UseCase(Gateway gateway) {
        this.gateway = gateway;
    }

    public void execute(String id, String name)
            throws CityExistedException, InvalidCityException {
            var city = new City(id,name);
            this.gateway.insertCity(city);
    }
}
