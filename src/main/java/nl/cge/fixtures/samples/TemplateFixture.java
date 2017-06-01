package nl.cge.fixtures.samples;

import nl.cge.template.Template;
import nl.cge.template.TemplateEngine;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 25-05-17.
 */
public class TemplateFixture {

    private TemplateEngine templateEngine = new TemplateEngine();

    private LocalDate datum = LocalDate.now();
    private String name;

    public void datum(String datum) {
        this.datum = LocalDate.parse(datum);
    }

    public void naam(String name) {
        this.name = name;
    }

    public String maakBericht() {
        Map<String, Object> templateVars = new HashMap<String, Object>();
        templateVars.put("datum", datum);
        templateVars.put("naam", name);
        return templateEngine.merge(Template.MYTEMPLATE, templateVars);
    }
}
