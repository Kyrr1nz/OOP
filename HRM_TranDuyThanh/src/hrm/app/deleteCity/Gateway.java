package hrm.app.deleteCity;

import hrm.core.InvalidCityException;

public interface Gateway {
    void deletecityId(String id)
            throws CityDeleteExistedException,
            CityDeleteHasEmployeesException,
            InvalidCityException;
}
