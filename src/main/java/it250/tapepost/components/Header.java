/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

//    @Inject
//    private ComponentResources resources;
//
//    @SessionState
//    private Member loggedInUser;
//
//    // return name of the logged user
//    public String getLoggedUser() {
//        return loggedInUser.getMemberFullName();
//    }
    //Check if user is Admin and can access admin area
    Object onEnterAdminArea() {
        if (loggedInMember.getMemberRole().equals(MemberRole.Administrator)) {
            return AdminArea.class;
        }
        return Dashboard.class;
    }

    Object onLogout() {
        resources.discardPersistentFieldChanges();
        return Index.class;
    }

    Object onActivate() {
        if (loggedInMember == null) {
            return Index.class;
        }
        return null;
    }
}
