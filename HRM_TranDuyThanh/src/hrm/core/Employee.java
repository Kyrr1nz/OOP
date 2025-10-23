package hrm.core;

import java.time.LocalDate;

public class Employee {
  private final String id;
    private final String firstname;
    private final String lastname;
    private final LocalDate birthday;
    private final String homeCityId;
    public Employee(String id, String firstname, String lastname, LocalDate birthday, String homeCityId)
            throws InvalidEmployeeException {
        if(id.isBlank()){
            throw  new InvalidEmployeeException("Id cannot be blank");
        }
        if(id.length() != 4){
            throw new InvalidEmployeeException("Id length must be 4");
        }
        if(firstname.isBlank()){
            throw  new InvalidEmployeeException("First Name cannot be blank");
        }
        if(firstname.length() > 45){
            throw new InvalidEmployeeException("First Name length must be greater than 45");
        }
        if(lastname.isBlank()){
            throw  new InvalidEmployeeException("Last Name cannot be blank");
        }
        if(lastname.length() > 45){
            throw new InvalidEmployeeException("Last Name length must be greater than 45");
        }
        if(birthday.isAfter(LocalDate.now())){
            throw  new InvalidEmployeeException("Birthday cannot be in future");
        }
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.homeCityId = homeCityId;
    }
//    public Employee(String id) throws InvalidEmployeeException {
//        if (id.isBlank()) {
//            throw new InvalidEmployeeException("Id cannot be blank");
//        }
//        if (id.length() != 4) {
//            throw new InvalidEmployeeException("Id length must be 4");
//        }
//        this.id = id;
//        this.firstname = "";
//        this.lastname = "";
//        this.birthday = LocalDate.now();
//        this.homeCityId = "";
//    }

    public String id(){return id;}
    public String firstname(){return firstname;}
    public String lastname(){return lastname;}
    public LocalDate birthday(){return birthday;}
    public String homeCityId(){return homeCityId;}

}