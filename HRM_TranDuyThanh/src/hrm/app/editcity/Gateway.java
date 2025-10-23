package hrm.app.editcity;

import hrm.core.InvalidCityException;

public interface Gateway {
    void editCity(String id, String newName)
            throws CityNotFoundException, InvalidCityException;
}
