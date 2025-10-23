package hrm.app.editcity;

import hrm.core.InvalidCityException;

public class UseCase {
    private final Gateway gateway;

    public UseCase(Gateway gateway) {
        this.gateway = gateway;
    }

    public void execute(String id, String newName)
            throws CityNotFoundException, InvalidCityException {
        gateway.editCity(id, newName);
    }
}
