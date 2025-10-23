package hrm.app.addemployee;


import hrm.core.InvalidEmployeeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Controller {
    private final UseCase uc;

    public Controller(UseCase uc) {
        this.uc = uc;
    }
    public void execute(String[] args)  {
        if (args.length != 5) {
            System.err.println("Args must be : <Id> <Firstname> <Lastname> <Birthday> <HomeCityId>");
            return;
        }
        var id = args[0];
        var firstname = args[1];
        var lastname = args[2];
        LocalDate birthday;
        try{
            birthday = LocalDate.parse(args[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (DateTimeParseException e ){
            System.err.println("Birthday must be in format day dd/MM/yyyy");
            return;
        }
        var homeCityId = args[4];

        try{
            this.uc.execute(id,firstname,lastname,birthday,homeCityId);
        }catch (InvalidEmployeeException e){
            System.err.println("Invalid Employee" + e.getMessage());
            return;
        }catch (EmployeeExistedException e){
            System.err.println("Employee with id " + e.employeeId() + " already exists");
            return;
        }catch (HomeCityNotFoundException e) {
            System.err.println("Employee with id " + e.cityId() + " not found");
            return;
            }
        System.out.println("Employee was added OK !");
    }
}
