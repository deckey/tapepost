package it250.tapepost.pages.view;

import it250.tapepost.data.MemberDAO;
import it250.tapepost.data.PostDAO;
import it250.tapepost.entities.Comment;
import it250.tapepost.entities.Member;
import it250.tapepost.entities.Post;
import it250.tapepost.pages.Dashboard;
import it250.tapepost.prop.MemberRole;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class ViewMember {
    
    @Property
    private List<Comment> comments;
    
    @InjectPage
    private Dashboard dashboardPage;
    
    @Inject
    private MemberDAO memberDao;

    @SessionState
    private Member loggedInMember;

    @Property
    @Persist
    private Member member;

    @Property
    private List<Member> members;

    @Property
    private List<Post> memberPosts;

    @Property
    private String passwordFormat;

    @Inject
    private PostDAO postDao;

    @Property
    private List<Post> posts;

    /**
     *
     * @return
     */
    public List<Comment> getMemberComments() {
        List<Comment> memberComments = new ArrayList<>();
        comments = postDao.findAllComments();
        for (Comment comment : comments) {
            if (comment.getCommentMemberId().equals(member.getMemberId())) {
                memberComments.add(comment);
            }
        }
        return memberComments;
    }

    /**
     * Return specified date in a readable format
     *
     * @param date
     * @return Date as a string, e.g. Jan 09
     */
    public String getFormattedDate(Date date) {
        return dashboardPage.getFormattedDate(date);
    }

    /**
     * Find access role level of the logged in user
     * @return True if user is Admin
     */
    public boolean getLoggedInRole() {
        if ((loggedInMember.getMemberRole().equals(MemberRole.Administrator))
                || (loggedInMember.getMemberId() == member.getMemberId())) {
            return true;
        }
        return false;
    }

    /**
     * Page activation context method to display a member selected from a different page
     * @param member
     */
    public void set(Member member) {
        this.member = member;
    }

    void onActivate(Member member) {
        this.member = member;
        passwordFormat = member.getMemberPassword().replaceAll(".", "*");
        members = memberDao.findAllMembers();
    }

    Member onPassivate() {
        return member;
    }
}
