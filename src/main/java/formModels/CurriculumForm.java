package formModels;

import javax.persistence.Entity;
import java.sql.Date;

import exportLibrary.DocExt;

@Entity
public class CurriculumForm extends Form {

    private String firstname;
    private String lastname;
    private Date dateofBirth;
    private String address;

    private final DocExt documentType = DocExt.DOCX;

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

    public Date getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(Date dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
