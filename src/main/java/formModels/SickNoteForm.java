package formModels;

import businessLogic.JsonMapper;
import org.json.simple.JSONArray;

import javax.persistence.Entity;

@Entity
public class SickNoteForm extends Form {

    public SickNoteForm(String name, String category) {
        super(name, category);

        JSONArray form = new JSONArray();
        form.add(JsonMapper.createField("doctor", "text"));
        form.add(JsonMapper.createField("doctorAddress", "text"));
        form.add(JsonMapper.createField("doctorPhone", "number"));

        form.add(JsonMapper.createField("patientFirstname", "text"));
        form.add(JsonMapper.createField("patientLastname", "text"));
        form.add(JsonMapper.createField("patientDateofBirth", "date"));
        form.add(JsonMapper.createField("patientBirthPlace", "date"));

        form.add(JsonMapper.createField("sickness", "text"));
        form.add(JsonMapper.createField("beginningDate", "date"));
        form.add(JsonMapper.createField("endingDate", "date"));

        this.fields = form.toJSONString();
    }

    public SickNoteForm() {
        super();
    }
}
