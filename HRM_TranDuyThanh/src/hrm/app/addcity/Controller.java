package hrm.app.addcity;

import hrm.core.InvalidCityException;
import hrm.app.addcity.*;

public class Controller {
    private final UseCase uc;

    public Controller(UseCase uc) {
        this.uc = uc;
    }
    public void execute(String[] args)  {
        if (args.length != 2) {
            System.err.println("Args must be : <id> <name>");
            return;
        }
        var id = args[0];
        var name = args[1];
        try{
            this.uc.execute(id, name);
        }catch (InvalidCityException e ){
            System.err.println("Invalid City" + e.getMessage());
            return;
        } catch (CityExistedException e) {
            System.err.println("City with id " + e.cityId() + " does not exist");
            return;

        }
        System.out.println("City was added OK !");
    }
}
