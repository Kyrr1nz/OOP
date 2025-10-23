package hrm.app.listallcity;

public class Controller {
    private final Usecase uc;
    public Controller(Usecase uc)
    {
        this.uc = uc;
    }
    public void execute() {
        var list = this.uc.execute();
        System.out.println("Number of cities : " + list.size());
        for (var c : list) {
            System.err.printf("[%s] %s : %d ",c.id(),c.name(),c.employeeNumber());
            System.err.println();
        }
    }
}
