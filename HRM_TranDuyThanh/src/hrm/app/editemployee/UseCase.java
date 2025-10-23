package hrm.app.editemployee;

import hrm.core.InvalidEmployeeException;
import java.time.LocalDate;

public class UseCase {
    private final Gateway gateway;

    public UseCase(Gateway gateway) {
        this.gateway = gateway;
    }

    public void execute(String id, String newFirstName, String newLastName, LocalDate newBirthday, String homeCityId)
            throws EmployeeNotFoundException, InvalidEmployeeException {
        gateway.editEmployee(id, newFirstName, newLastName, newBirthday, homeCityId);
    }
}
