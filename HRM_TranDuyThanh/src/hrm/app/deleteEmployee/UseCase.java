package hrm.app.deleteEmployee;
import hrm.core.InvalidEmployeeException;
import hrm.core.Employee;

import java.util.ArrayList;
public class   UseCase {
    private final Gateway gateway;

    public UseCase(Gateway gateway) {
        this.gateway = gateway;
    }

    public void execute(String employeeId)
            throws HomeCityDeleteNotFoundException,
            EmployeeDeleteExistedException,
            InvalidEmployeeException {
        gateway.deleteEmployee(employeeId);
    }
    }
