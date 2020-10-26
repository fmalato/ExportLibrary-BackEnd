package formModels;

import databaseManagement.JsonMapper;
import org.json.simple.JSONArray;

import javax.persistence.Entity;

@Entity
public class TuscanyCovidForm extends Form {

    public TuscanyCovidForm(String name, String category) {
        super(name, category);

        JSONArray form = new JSONArray();

        form.add(getProvinceForm("Arezzo"));
        form.add(getProvinceForm("Firenze"));
        form.add(getProvinceForm("Grosseto"));
        form.add(getProvinceForm("Livorno"));
        form.add(getProvinceForm("Lucca"));
        form.add(getProvinceForm("Massa-Carrara"));
        form.add(getProvinceForm("Pisa"));
        form.add(getProvinceForm("Pistoia"));
        form.add(getProvinceForm("Prato"));
        form.add(getProvinceForm("Siena"));

        this.fields = form.toJSONString();
    }

    public TuscanyCovidForm() {
        super();
    }

    private JSONArray getProvinceForm(String province) {
        JSONArray form = new JSONArray();

        form.add(JsonMapper.createField("province", "text", province));
        form.add(JsonMapper.createField("dailyCases", "number"));
        form.add(JsonMapper.createField("globalCases", "number"));
        form.add(JsonMapper.createField("recovered", "number"));
        form.add(JsonMapper.createField("deaths", "number"));
        form.add(JsonMapper.createField("intensiveCares", "number"));

        return form;
    }

}