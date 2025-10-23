package hrm.app.addemployee;
import hrm.app.addcity.CityExistedException;
import hrm.core.City;
import hrm.core.Employee;

public interface Gateway {
    void insertEmployee(Employee e)
            throws EmployeeExistedException,HomeCityNotFoundException;
}
