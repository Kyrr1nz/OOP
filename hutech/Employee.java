package hutech.domain;

public  class Employee {

    private float salaryPerHour  ;
    public Employee(){
        this.salaryPerHour = 0.0f;
    }
    public Employee(float salary) {
        if (salary >= 0) {
            this.salaryPerHour = salary;
        } else {
            this.salaryPerHour = 0.0f;
        }
    }
    static int sum(int a, int b){

        return a+b;
    }

    public float getSalary(int numDays) {
        int totalsHours = sum(numDays,0)*8;
        return numDays * 8 * salaryPerHour;

    }
    public float getSalary(int numMonths, int numDays) {
        int totalDays = numMonths * 30 + numDays;

        return getSalary(totalDays);
    }
    public void setSalaryPerHour(float salaryPerHour) {
        if (salaryPerHour >= 0) {
            this.salaryPerHour = salaryPerHour;
        }else {
            this.salaryPerHour = 0.0f;
        }
    }
    public float getSalaryPerHour() {
        return salaryPerHour;
    }
}
