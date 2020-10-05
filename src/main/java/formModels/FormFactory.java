package formModels;


public class FormFactory {

    public FormFactory() {

    }

    public Form createForm(Long formId, String formName, String category) {

        switch (formId.intValue()) {
            case 1:
                return new CurriculumForm(formName, category);
            case 2:
                System.out.println("Form 2");
            case 3:
                System.out.println("Form 3");
            case 4:
                System.out.println("Form 4");
        }
        return null;
        
    }

}
