package formModels;


public class FormFactory {

    public FormFactory() { }

    public Form createForm(String formName, String category) {

        switch (category) {
            case "Curriculum":
                return new CurriculumForm(formName, category);
            case "Certificato di Malattia":
                return new SickNoteForm(formName, category);
            case "Salari Ospedale":
                return new HospitalSalaryForm(formName, category);
            case "COVID Toscana":
                return new TuscanyCovidForm(formName, category);
        }
        System.out.println("Form not found");
        throw new RuntimeException();

    }

}
