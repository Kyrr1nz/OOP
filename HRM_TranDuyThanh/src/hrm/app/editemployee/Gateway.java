package hrm.app.editemployee;

import hrm.core.InvalidEmployeeException;

import java.time.LocalDate;;

public interface Gateway {
    void editEmployee(String id, String newFirstName, String newLastName, LocalDate newBirthday, String HomeCityId)
            throws EmployeeNotFoundException, InvalidEmployeeException;
}
