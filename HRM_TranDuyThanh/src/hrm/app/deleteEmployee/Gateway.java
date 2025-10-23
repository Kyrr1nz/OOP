package hrm.app.deleteEmployee;

import hrm.core.InvalidEmployeeException;

public interface Gateway {
    void deleteEmployee(String id)
            throws HomeCityDeleteNotFoundException,
            EmployeeDeleteExistedException,
            InvalidEmployeeException;
}
