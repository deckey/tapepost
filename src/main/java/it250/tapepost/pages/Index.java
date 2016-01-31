package it250.tapepost.pages;

import it250.tapepost.data.MemberDAO;
import it250.tapepost.entities.Member;
import java.util.List;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

/**
 * Start page of application TapePost.
 */
public class Index {

    @Inject
    private AlertManager alertManager;

    @Inject
    private Session session;

    @Property
    @SessionState
    private Member loggedInMember;

    @Inject
    private MemberDAO memberDao;

    @Property
    private Member member;

    private List<Member> members;

    @Property
    @Validate("required")
    private String memberPassword;

    @Property
    @Validate("required")
    private String memberUsername;

  
    Object onValidateFromLoginForm() {
        if (memberDao.validateMember(memberUsername, memberPassword)) {
            loggedInMember = memberDao.findMemberByUsername(memberUsername);
            return Dashboard.class;
        } else {
            alertManager.alert(Duration.TRANSIENT, Severity.ERROR, "Login failed, wrong username or password!");
            return null;
        }
    }
}
