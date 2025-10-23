package hrm.app.addemployee;

public class EmployeeExistedException extends Exception {
    private final String employeeId;

    public EmployeeExistedException(String employeeId) {
        this.employeeId = employeeId;
    }
    public String employeeId() {
        return this.employeeId;
    }
}
