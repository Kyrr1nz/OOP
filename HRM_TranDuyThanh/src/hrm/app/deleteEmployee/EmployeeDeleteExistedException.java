package hrm.app.deleteEmployee;

public class EmployeeDeleteExistedException  extends Exception{
    private final String employeeId;
    public EmployeeDeleteExistedException (String employeeId) {
        super ("EmployeeId " + employeeId + "not found");
        this.employeeId = employeeId;
    }
    public String EmployeeId() {
        return employeeId;
    }
}
