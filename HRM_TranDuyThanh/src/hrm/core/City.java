package hrm.core;

public class City {
    private final String id;
    private final String name;

    public City(String id, String name)throws InvalidCityException {
        if(id.isBlank()){
            throw  new InvalidCityException("Id cannot be blank");
        }
        if(id.length() != 2){
            throw new InvalidCityException("Id length must be 2");
        }
        if(name.isBlank()){
            throw  new InvalidCityException("Id cannot be blank");
        }
        if(name.length() > 45){
            throw new InvalidCityException("Id length must be 2");
        }
        this.id = id;
        this.name = name;
    }
    public String id(){return id;}
    public String name(){return this.name;}
}
