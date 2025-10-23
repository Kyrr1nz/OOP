package hrm.app.editemployee;

import hrm.core.InvalidEmployeeException;

import java.time.LocalDate;

public class Controller {
    private final UseCase useCase;

    public Controller(UseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(String[] args) {
        if (args.length < 5) {
            System.out.println("Usage: edit-employee <id> <new_firstname> <new_lastname> <yyyy-mm-dd> <home_city_id>");
            return;
        }

        var id = args[0];
        var newFirstName = args[1];
        var newLastName = args[2];
        var newBirthday = LocalDate.parse(args[3]);
        var homeCityId = args[4];

        try {
            useCase.execute(id, newFirstName, newLastName, newBirthday, homeCityId);
            System.out.println("Employee updated successfully!");
        } catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidEmployeeException e) {
            System.out.println("Invalid data: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
