package formModels;

import databaseManagement.JsonMapper;
import org.json.simple.JSONArray;

import javax.persistence.Entity;


@Entity
public class CurriculumForm extends Form {

    public CurriculumForm(String name, String category) {
        super(name, category);

        JSONArray form = new JSONArray();
        form.add(JsonMapper.createField("firstname", "text"));
        form.add(JsonMapper.createField("lastname", "text"));
        form.add(JsonMapper.createField("dateofBirth", "date"));
        form.add(JsonMapper.createField("address", "text"));
        form.add(JsonMapper.createField("phone", "number"));
        form.add(JsonMapper.createField("mail", "text"));
        form.add(JsonMapper.createField("list", "list"));
        form.add(JsonMapper.createField("image", "image"));

        this.fields = form.toJSONString();
    }

    public CurriculumForm() {
        super();
    }

}
