package nl.cge.template;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.Map;

/**
 * Created by chris on 25-05-17.
 */
public class TemplateEngine {

    static {
        Velocity.addProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        Velocity.addProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        Velocity.init();
    }

    public String merge(Template template, Map<String, Object> templateVars) {
        org.apache.velocity.Template vTemplate = Velocity.getTemplate("/templates/" + template.templateName());
        VelocityContext context = new VelocityContext();
        for (String key : templateVars.keySet()) {
            context.put(key, templateVars.get(key));
        }
        StringWriter stringWriter = new StringWriter();
        vTemplate.merge(context, stringWriter);
        return stringWriter.toString();
    }

}
