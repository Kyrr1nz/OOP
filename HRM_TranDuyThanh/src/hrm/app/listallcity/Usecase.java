package hrm.app.listallcity;


import java.util.ArrayList;

public class Usecase {
    private final GateWay gateway;
    public Usecase(GateWay gateway) {
        this.gateway = gateway;
    }
    public ArrayList<CityInfo> execute() {
        return this.gateway.getAllCity();
    }
}
