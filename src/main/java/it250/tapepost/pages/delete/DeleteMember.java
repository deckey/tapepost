package it250.tapepost.pages.delete;

import it250.tapepost.data.MemberDAO;
import it250.tapepost.data.PostDAO;
import it250.tapepost.entities.Comment;
import it250.tapepost.entities.Member;
import it250.tapepost.entities.Post;
import it250.tapepost.pages.Index;
import it250.tapepost.prop.MemberRole;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class DeleteMember {

    @Inject
    private AlertManager alertManager;

    @Property
    private List<Comment> comments;

    @SessionState
    private Member loggedInMember;
    @Inject
    private MemberDAO memberDao;

    @Property
    @Persist
    private Member member;

    @Inject
    private PostDAO postDao;

    @Property
    private List<Post> posts;

    @Property
    private List<Post> memberPosts;
    @Inject
    private ComponentResources resources;

    /**
     * Find all comments made by current member
     * @return List of comments 
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
     * Page activation context method to display a member selected from a different page
     * @param member
     */
    public void set(Member member) {
        this.member = member;
    }

    void onActivate(Member member) {
        this.member = member;
        memberPosts = member.getPosts();
    }

    Member onPassivate() {
        return member;
    }

    @CommitAfter
    Object onDeleteMember(Integer id) {
        Member member = memberDao.findMemberById(id);
        if (loggedInMember.getMemberUsername().equals(member.getMemberUsername())
                && (loggedInMember.getMemberRole().equals(MemberRole.Administrator))) {
            alertManager.alert(Duration.TRANSIENT, Severity.ERROR, "Administrator can not delete his own profile!?");
            return null;
        }
        List<Post> memberPosts = member.getPosts();
        List<Comment> postComments = new ArrayList<>();
        List<Comment> memberComments = getMemberComments();

        System.out.println("DELETING COMMENTS.......");
        for (Comment comment : memberComments) {
            System.out.println("COMMENT: " + comment.getCommentContent());
            postDao.deleteComment(comment);
        }
        System.out.println("......COMMENTS DELETED");

        System.out.println("DELETING POST COMMENTS.......");
        for (Post post : memberPosts) {
            postComments = post.getComments();
            for (Comment comment : postComments) {
                postDao.deleteComment(comment);
            }
        }
        System.out.println("......POST COMMENTS DELETED");
        member.setPosts(new ArrayList<>());
        System.out.println("DELETING POSTS.......");
        postDao.deleteAllPosts(memberPosts);
        System.out.println("DELETING MEMBER.......");
        memberDao.deleteMember(id);
        resources.discardPersistentFieldChanges();
        return Index.class;
    }
}
