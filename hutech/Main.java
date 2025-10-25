package hutech;
public class Main {
    public static void main(String[] args) {

//        String message = args[0] + " " + args[1] + " " + args[2];
//
//        for (int i = 0; i < 100; i++) {
//            System.out.println(message);
//        }
        hutech.domain.Employee e = new hutech.domain.Employee();
        System.out.println("Salary per hour: " + e.getSalaryPerHour());

        hutech.domain.Employee emp = new hutech.domain.Employee(7.0f);
        System.out.println("Salary per hour: " + emp.getSalaryPerHour());


    }
}

