package hrm;

import hrm.db.MySqlGateway;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if(args.length==0){
            System.out.println("Args can't be empty");
            return;
        }

        var gateway = new MySqlGateway("jdbc:mysql://localhost:3306/hrm","root","123456a@");
        var cmd = args[0];
        var remainArgs = Arrays.copyOfRange(args, 1, args.length);
        switch (cmd){
            case "add-city" ->{
                var uc = new hrm.app.addcity.UseCase(gateway);
                var controller = new hrm.app.addcity.Controller(uc);
                controller.execute(remainArgs);
            }
            case "add-employee" ->{
                var uc = new hrm.app.addemployee.UseCase(gateway);
                var controller = new hrm.app.addemployee.Controller(uc);
                controller.execute(remainArgs);
            }
            case "list-all-cities" ->{
                var uc = new hrm.app.listallcity.Usecase(gateway);
                var controller = new hrm.app.listallcity.Controller(uc);
                controller.execute();
            }
            case "list-all-employees" ->{
                var uc = new hrm.app.listallemployee.Usecase(gateway);
                var controller = new hrm.app.listallemployee.Controller(uc);
                controller.execute();
            }

            case "delete-employee" -> {
                if (remainArgs.length < 1) {
                    System.err.println("Usage: delete-employee <employee_id>");
                    return;
                }
                var uc = new hrm.app.deleteEmployee.UseCase(gateway);
                var controller = new hrm.app.deleteEmployee.Controller(uc);
                controller.execute(remainArgs);
            }
            case "delete-city" -> {
                if (remainArgs.length < 1) {
                    System.err.println("Usage: delete-city <city_id>");
                    return;
                }
                var uc = new hrm.app.deleteCity.UseCase(gateway);
                var controller = new hrm.app.deleteCity.Controller(uc);
                controller.execute(remainArgs);
            }
            case "edit-city" -> {
                var uc = new hrm.app.editcity.UseCase(gateway);
                var controller = new hrm.app.editcity.Controller(uc);
                controller.execute(remainArgs);
            }
            case "edit-employee" -> {
                var uc = new hrm.app.editemployee.UseCase(gateway);
                var controller = new hrm.app.editemployee.Controller(uc);
                controller.execute(remainArgs);
            }

            default -> System.out.println("Unknown command " + cmd);
        }
    }
}