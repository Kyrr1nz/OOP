package hrm.app.listallemployee;
import  java.time.format.DateTimeFormatter;
public class Controller {
    private final Usecase uc;
    public Controller(Usecase uc) {
        this.uc = uc;
    }
    public void execute() {
        var list = this.uc.execute();
        System.out.println("Number of Employee : " + list.size());
        for (var e : list) {
            System.err.printf("[%s] %s %s (%s - %s) " ,
                    e.id(),
                    e.firstname(),
                    e.lastname(),
                    e.birthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    e.homeCityName() );
            System.err.println();
        }
    }
}
