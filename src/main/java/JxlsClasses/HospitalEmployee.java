package JxlsClasses;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class HospitalEmployee {
    private String firstname;
    private String lastname;
    private String job;
    private int salary;
    private int extraHours;
    private int hourlyPay;

    public HospitalEmployee(String firstname, String lastname, String job, int salary, int extraHours) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.job = job;
        this.salary = salary;
        this.extraHours = extraHours;
        if (this.job.equals("Infermiere")) {
            this.hourlyPay = 15;
        } else if (this.job.equals("Medico")) {
            this.hourlyPay = 25;
        } else {
            this.hourlyPay = 10;
        }
    }

    public HospitalEmployee() {
        this.firstname = "";
        this.lastname = "";
        this.job = "";
        this.salary = 0;
        this.extraHours = 0;
        this.hourlyPay = 0;
    }

    public static List<HospitalEmployee> getEmployees(ArrayList fields) {
        List<HospitalEmployee> employees = new ArrayList<>();
        for (Object element : fields) {
            try {
                employees.add(new HospitalEmployee(((LinkedHashMap) element).get("firstname").toString(),
                        ((LinkedHashMap) element).get("lastname").toString(),
                        ((LinkedHashMap) element).get("job").toString(),
                        (int) ((LinkedHashMap) element).get("salary"),
                        (int) ((LinkedHashMap) element).get("extraHours")));
            } catch (Exception e) { continue; }
        }
        return employees;
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

    public int getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(int hourlyPay) {
        this.hourlyPay = hourlyPay;
    }
}
