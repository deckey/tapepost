package it250.tapepost.components;

import it250.tapepost.entities.Member;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class MainMenu {
    
    @Inject
    private ComponentResources componentResource;

    @Property
    @SessionState
    private Member loggedInMember;
    @Property
    private String pageName;
    @Property
    private String subPageName;

    /**
     * Create and return a list of all main pages
     * @return string list of all page names
     */
    public List<String> getPageNames() {
        List<String> pageNames = new ArrayList<>(Arrays.asList("Dashboard", "Posts", "Members"));
        return pageNames;
    }

    /**
     * Check if a page is current and return result to change css class
     * @return string 'current' if true and null if false
     */
    public String getClassForPage() {
        return componentResource.getPageName().equalsIgnoreCase(pageName) ? "current" : null;
    }

    /**
     * Return label of a page based on page's name (so we can name pages different than their class names'
     * @return  of the page label
     */
    public String getPageLabel() {
        List<String> pageNames = getPageNames();
        String[] pageLabels = {"Dashboard", "Posts", "Members"};
        return pageLabels[pageNames.indexOf(pageName)];
    }

    /**
     * Return a tooltip for a given page name
     * @return string tooltip content for a page name
     */
    public String getPageTooltip() {
        List<String> pageNames = getPageNames();
        String[] pageTooltips = {"Go to overview", "Check posts", "View members"};
        return pageTooltips[pageNames.indexOf(pageName)];
    }


}
