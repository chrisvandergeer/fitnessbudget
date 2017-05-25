package nl.cge.fixtures;

import nl.cge.template.Template;
import nl.cge.template.TemplateEngine;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 25-05-17.
 */
public class TemplateFixture extends TemplateEngine {

    private LocalDate datum = LocalDate.now();
    private String name;

    public void datum(String datum) {
        this.datum = LocalDate.parse(datum);
    }

    public void naam(String name) {
        this.name = name;
    }

    public String maakBericht() {
        return merge();
    }

    @Override
    protected Template getTemplate() {
        return Template.MYTEMPLATE;
    }

    @Override
    protected Map<String, Object> getTemplateVars(Map<String, Object> vars) {
        vars.put("datum", datum);
        vars.put("naam", name);
        return vars;
    }
}
