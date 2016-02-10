package it250.tapepost.pages.view;

import it250.tapepost.data.MemberDAO;
import it250.tapepost.data.PostDAO;
import it250.tapepost.entities.Comment;
import it250.tapepost.entities.Member;
import it250.tapepost.entities.Post;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class ViewPostDetails {

    @Property
    private Comment comment;

    @SessionState
    @Property
    private Member loggedInMember;
    
    @Inject
    private MemberDAO memberDao;
    
    @Inject
    private PostDAO postDao;

    @Property
    private Post post;

    @Property
    private List<Post> posts;

    @Inject
    private BeanModelSource beanModelSource;
    @Property
    private BeanModel<Comment> commentGridModel;
    @Inject
    private Messages messages;
    @Property
    private String commentContent;

    /**
     * Find user that made a specified comment
     * @param id
     * @return member's username as string
     */
    public String getCommentingMember(Integer id) {
        return memberDao.findMemberById(id).getMemberUsername();
    }

    void onPrepare() {
        commentGridModel = beanModelSource.createDisplayModel(Comment.class, messages);
        commentGridModel.get("commentContent").label("Content");
        commentGridModel.get("commentTime").label("Time added");
        commentGridModel.get("commentContent").sortable(false);
    }

    @CommitAfter
    void onSuccessFromAddCommentForm() {
        Comment newComment = new Comment(commentContent, post, loggedInMember);
        post.getComments().add(newComment);
        postDao.saveComment(newComment);
        postDao.updatePost(post);
    }

    void onActivate(Integer id) {
        this.post = postDao.findPostById(id);
    }

    /**
     * Page activation context method to display a post selected from a different page
     * @param id
     * 
     */
    public void set(Integer id) {
        this.post = postDao.findPostById(id);
    }

    Post onPassivate() {
        return this.post;
    }

}
