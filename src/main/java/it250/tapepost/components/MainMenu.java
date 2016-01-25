/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class MainMenu {
    
    @Inject
    private ComponentResources componentResource;

    @Property
    private String pageName;
    @Property
    private String subPageName;

    public List<String> getPageNames() {
        List<String> pageNames = new ArrayList<>(Arrays.asList("Dashboard", "Posts", "Members"));
        return pageNames;
    }

    public String getClassForPage() {
        return componentResource.getPageName().equalsIgnoreCase(pageName) ? "current" : null;
    }
    public String getPageLabel() {
        List<String> pageNames = getPageNames();
        String[] pageLabels = {"Dashboard", "Posts", "Members"};
        return pageLabels[pageNames.indexOf(pageName)];
    }
    public String getPageTooltip() {
        List<String> pageNames = getPageNames();
        String[] pageTooltips = {"Go to overview", "Check posts", "View members"};
        return pageTooltips[pageNames.indexOf(pageName)];
    }


}
