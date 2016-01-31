package it250.tapepost.components;

import it250.tapepost.entities.Member;
import it250.tapepost.pages.AdminArea;
import it250.tapepost.pages.Dashboard;
import it250.tapepost.pages.Index;
import it250.tapepost.prop.MemberRole;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class Header {

    @Property
    @SessionState
    private Member loggedInMember;
    @Inject
    private ComponentResources resources;

    /**
     * Check if user is Admin and can access admin area
     *
     * @return AdminArea if true or Dashboard page if false
     */
    public Object onEnterAdminArea() {
        if (loggedInMember.getMemberRole().equals(MemberRole.Administrator)) {
            return AdminArea.class;
        }
        return Dashboard.class;
    }

    /**
     * Clear persistent user from session and go to Index page
     *
     * @return Index page
     */
    public Object onLogout() {
        resources.discardPersistentFieldChanges();
        return Index.class;
    }

    /**
     * Check if member is logged or not
     * @return Index if true or null if false
     */
    Object onActivate() {
        if (loggedInMember == null) {
            return Index.class;
        }
        return null;
    }
}
