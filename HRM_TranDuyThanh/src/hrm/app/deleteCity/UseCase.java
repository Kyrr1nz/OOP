package hrm.app.deleteCity;
import hrm.core.InvalidCityException;
import hrm.core.Employee;

import java.util.ArrayList;
public class   UseCase {
    private final Gateway gateway;

    public UseCase(Gateway gateway) {
        this.gateway = gateway;
    }

    public void execute(String cityId)
            throws CityDeleteExistedException,
            CityDeleteHasEmployeesException,
            InvalidCityException {
        gateway.deletecityId(cityId);
    }
}
