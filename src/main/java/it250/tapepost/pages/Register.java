/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.pages;

import it250.tapepost.data.MemberDAO;
import it250.tapepost.entities.Member;
import it250.tapepost.prop.MemberRole;
import java.util.List;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Import(library = {"context:mybootstrap/js/custom/registration.js"})
public class Register {

    @Inject
    private AlertManager alertManager;

    @Component
    private Form registerForm;

    @InjectPage
    private Index indexPage;

    @Property
    @SessionState
    private Member loggedInMember;
    @Inject
    private MemberDAO memberDao;

    private List<Member> members;

    @Property
    @Persist
    @Validate("required")
    private String memberFullName;

    @Property
    @Persist
    @Validate("required")
    private String memberPassword;

    @Property
    @Validate("required")
    private String memberRepeatPassword;

    @Property
    @Persist
    @Validate("required")
    private String memberUsername;

    void onActivate() {
        members = memberDao.findAllMembers();
        memberFullName = "";
        memberUsername = "";
        memberPassword = "";
    }

    @CommitAfter
    Object onValidateFromRegisterForm() {
        for (Member mem : members) {
            System.out.println("MEMBER: " + mem.getMemberUsername());
            if (memberUsername.equals(mem.getMemberUsername())) {
                alertManager.alert(Duration.TRANSIENT, Severity.ERROR, "Username " + memberUsername + " already exists!");
                return null;
            }
        }
        Member newMember = new Member(memberFullName, memberUsername, memberPassword, MemberRole.User);
        memberDao.saveMember(newMember);
        loggedInMember = newMember;
        return Dashboard.class;
    }
}
