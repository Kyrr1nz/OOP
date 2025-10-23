package hrm.app.addemployee;



import hrm.core.Employee;
import hrm.core.InvalidEmployeeException;



import java.time.LocalDate;

public class UseCase {
    private final Gateway gateway;

    public UseCase(Gateway gateway) {
        this.gateway = gateway;
    }

    public void execute(String id,String firstname,String lastname,LocalDate birthday,String homeCityId)
            throws
            EmployeeExistedException,
            InvalidEmployeeException,
            HomeCityNotFoundException
    {
            var e = new Employee(id, firstname,lastname, birthday,homeCityId);

            this.gateway.insertEmployee(e);
    }

}
