package JxlsClasses;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TuscanyProvince {
    private String province;
    private int dailyCases;
    private int globalCases;
    private int recovered;
    private int deaths;
    private int intensiveCares;
    private int numProvince;

    public TuscanyProvince(String province, int dailyCases, int globalCases, int recovered, int deaths, int intensiveCares) {
        this.province = province;
        this.dailyCases = dailyCases;
        this.globalCases = globalCases;
        this.recovered = recovered;
        this.deaths = deaths;
        this.intensiveCares = intensiveCares;
        this.numProvince = 0;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getDailyCases() {
        return dailyCases;
    }

    public void setDailyCases(int dailyCases) {
        this.dailyCases = dailyCases;
    }

    public int getGlobalCases() {
        return globalCases;
    }

    public void setGlobalCases(int globalCases) {
        this.globalCases = globalCases;
    }

    public int getIntensiveCares() {
        return intensiveCares;
    }

    public void setIntensiveCares(int intensiveCares) {
        this.intensiveCares = intensiveCares;
    }

    public int getNumProvince() { return this.numProvince; }

    public void setNumProvince(int numProvince) { this.numProvince = numProvince; }

    public TuscanyProvince() {
        this.province = "";
        this.dailyCases = 0;
        this.globalCases = 0;
        this.recovered = 0;
        this.deaths = 0;
        this.intensiveCares = 0;
        this.numProvince = 0;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public static List<TuscanyProvince> getProvinces(ArrayList fields) {
        List<TuscanyProvince> provinces = new ArrayList<>();
        for (Object element : fields) {
            try {
                provinces.add(new TuscanyProvince(((LinkedHashMap) element).get("province").toString(),
                        (int) ((LinkedHashMap) element).get("dailyCases"),
                        (int) ((LinkedHashMap) element).get("globalCases"),
                        (int) ((LinkedHashMap) element).get("recovered"),
                        (int) ((LinkedHashMap) element).get("deaths"),
                        (int) ((LinkedHashMap) element).get("intensiveCares")));
            } catch (Exception e) { continue; }
        }
        for(TuscanyProvince prov : provinces) {
            prov.setNumProvince(fields.size());
        }
        return provinces;
    }
}
