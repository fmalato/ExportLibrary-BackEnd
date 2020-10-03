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
    private DocExt documentType;

    @NotNull
    private String category;

    public Form(String name, DocExt documentType, String category) {
        this.name = name;
        this.documentType = documentType;
        this.category = category;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
