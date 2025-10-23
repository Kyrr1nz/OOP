package hrm.app.listallemployee;
import hrm.core.Employee;

import java.util.ArrayList;

public class Usecase {
    private final GateWay gateway;
    public Usecase(GateWay gateway) {
        this.gateway = gateway;
    }
    public ArrayList<EmployeeInfo> execute() {
        return this.gateway.getAllEmployee();
    }
}
