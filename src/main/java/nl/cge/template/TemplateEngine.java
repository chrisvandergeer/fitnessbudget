package nl.cge.template;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 25-05-17.
 */
public abstract class TemplateEngine {

    static {
        Velocity.addProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        Velocity.addProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        Velocity.init();
    }

    public String merge() {
        org.apache.velocity.Template vTemplate = Velocity.getTemplate("/templates/" + getTemplate().getTemplateName());
        VelocityContext context = new VelocityContext();
        Map<String, Object> templateVars = getTemplateVars(new HashMap<String, Object>());
        for (String key : templateVars.keySet()) {
            context.put(key, templateVars.get(key));
        }
        StringWriter stringWriter = new StringWriter();
        vTemplate.merge(context, stringWriter);
        return stringWriter.toString();
    }

    protected abstract Template getTemplate();

    protected abstract Map<String, Object> getTemplateVars(Map<String, Object> vars);

}
