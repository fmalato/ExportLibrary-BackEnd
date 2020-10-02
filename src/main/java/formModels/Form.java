package formModels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import exportLibrary.DocExt;

@Entity
public abstract class Form {

    @Id
    @GeneratedValue
    private Long id;
    private DocExt documentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
