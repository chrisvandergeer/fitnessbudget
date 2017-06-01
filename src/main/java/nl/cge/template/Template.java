package nl.cge.template;

/**
 * Created by chris on 25-05-17.
 */
public enum Template {

    MYTEMPLATE("myTemplate.vm");

    private final String templateName;

    Template(String myTemplates) {
        this.templateName = myTemplates;
    }

    public String templateName() {
        return templateName;
    }
}
