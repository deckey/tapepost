package it250.tapepost.components;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;

/**
 * Layout component for pages of application test-project.
 */
@Import(stylesheet = {
    "context:mybootstrap/css/normalize.css",
    "context:mybootstrap/css/bootstrap-theme.css",
    "context:mybootstrap/css/main.css"
})
public class Layout {

    @Inject
    private ComponentResources resources;

   
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    private String pageName;

    /**
     * Return 'active' class for an active page, based on page name
     * @return 'active' if true or null if false
     */
    public String getClassForPageName() {
        return resources.getPageName().equalsIgnoreCase(pageName)
                ? "active"
                : null;
    }
}
