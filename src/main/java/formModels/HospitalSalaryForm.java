package formModels;

import businessLogic.JsonMapper;
import org.json.simple.JSONArray;

import javax.persistence.Entity;

@Entity
public class HospitalSalaryForm extends Form {

    public HospitalSalaryForm(String name, String category) {
        super(name, category);

        JSONArray form = new JSONArray();
        form.add(JsonMapper.createField("firstname", "text"));
        form.add(JsonMapper.createField("lastname", "text"));
        form.add(JsonMapper.createField("job", "text"));
        form.add(JsonMapper.createField("salary", "number"));
        form.add(JsonMapper.createField("extraHours", "number"));

        JSONArray extForm = new JSONArray();
        extForm.add(form);

        this.fields = extForm.toJSONString();
    }

    public HospitalSalaryForm() {
        super();
    }

}
