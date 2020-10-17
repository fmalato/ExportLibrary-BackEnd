package formModels;

public class HospitalEmployee {
    private String firstname;
    private String lastname;
    private String job;
    private int salary;
    private int extraHours;

    public HospitalEmployee(String firstname, String lastname, String job, int salary, int extraHours) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.job = job;
        this.salary = salary;
        this.extraHours = extraHours;
    }

    public HospitalEmployee() {
        this.firstname = "";
        this.lastname = "";
        this.job = "";
        this.salary = 0;
        this.extraHours = 0;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getExtraHours() {
        return extraHours;
    }

    public void setExtraHours(int extraHours) {
        this.extraHours = extraHours;
    }
}
