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

//
//    void test() {
//
//        System.out.println("STARTED.........");
//
//        Member member1 = new Member();
//        member1.setMemberUsername("username1");
//        Set<Post> posts = new HashSet<>();
//        Set<Comment> comments = new HashSet<>();
//
//        Post post1 = new Post();
//        post1.setPostContent("Post 1 content here");
//        post1.setPostTime(new Date());
//        Post post2 = new Post();
//        post2.setPostContent("Post 2 some content");
//
//        Comment comment1 = new Comment();
//        comment1.setCommentContent("This is comment 1 belongs to post 2");
//        comment1.setPost(post2);
//
//        Comment comment2 = new Comment();
//        comment2.setCommentContent("This is comment 2 belongs to post 2");
//        comment2.setPost(post2);
//
//        comments.add(comment1);
//        comments.add(comment2);
//
//        post2.setComments(comments);
//
//        post1.setMember(member1);
//        post2.setMember(member1);
//
//        posts.add(post1);
//        posts.add(post2);
//
//        member1.setPosts(posts);
//        session.save(member1);
//
//        System.out.println("...........SAVED");
//    }
//
//    @CommitAfter
//    void onTestDb() {
//        test();
//    }
}
