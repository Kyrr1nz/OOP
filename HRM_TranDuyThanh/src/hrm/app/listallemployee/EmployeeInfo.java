package hrm.app.listallemployee;

import java.time.LocalDate;
public record EmployeeInfo(String id,String firstname, String lastname,LocalDate birthday,String homeCityName) {
}
