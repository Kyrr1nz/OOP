package hrm.app.deleteCity;

import hrm.core.InvalidCityException;

public class Controller {
    private final UseCase useCase;

    public Controller(UseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: delete-city <city_id>");
            return;
        }

        var id = args[0];
        try {
            useCase.execute(id);
            System.out.println("City " + id + " deleted successfully!");
        } catch (InvalidCityException e) {
            System.err.println("Invalid city id: " + e.getMessage());
        } catch (CityDeleteExistedException e) {
            System.err.println("City not found: " + e.getMessage());
        } catch (CityDeleteHasEmployeesException e) {
            System.err.println("Cannot delete: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
