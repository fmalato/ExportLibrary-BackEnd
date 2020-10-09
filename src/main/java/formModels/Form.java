package formModels;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import exportLibrary.DocExt;


@Entity
public abstract class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String category;
    @Column(length = 1000)
    protected String fields;



    public Form(String name, String category) {
        this.name = name;
        this.category = category;
        this.fields = "[]";
    }

    public Form() {

    }

    public String getFields() {
        return fields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
