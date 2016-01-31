/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.pages;

import it250.tapepost.data.MemberDAO;
import it250.tapepost.data.PostDAO;
import it250.tapepost.entities.Comment;
import it250.tapepost.entities.Member;
import it250.tapepost.entities.Post;
import it250.tapepost.pages.view.ViewPostDetails;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Import(library = {"context:mybootstrap/js/custom/posts.js"})
public class Posts {

    @Property
    private Comment comment;

    @Property
    @Validate("required")
    private String commentContent;

    @Property
    @SessionState
    private Member loggedInMember;

    @Property
    private Member member;

    @InjectPage
    private Dashboard dashboardPage;

    @Inject
    private MemberDAO memberDao;

    @InjectPage
    private ViewPostDetails viewPostDetailsPage;

    @InjectComponent
    private Zone popularPostsZone;

    @Property
    @Persist
    private Post post;

    @Inject
    private PostDAO postDao;

    @Property
    private List<Post> posts;

    @Property
    private boolean postSelected = false;

    @Inject
    private Request request;

    @Inject
    private AjaxResponseRenderer response;

    @InjectComponent
    private Zone commentZone;

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
     *
     * Return specified time in a readable format
     *
     * @param date
     * @return Date as a time string, e.g. 12:15
     */
    public String getFormattedTime(Date date) {
        return dashboardPage.getFormattedTime(date);
    }

    /**
     * Find a Member instance that created a specified comment
     *
     * @param id
     * @return String of member's username
     */
    public String getCommentingMember(Integer id) {
        return memberDao.findMemberById(id).getMemberUsername();
    }

    /**
     * Find top 3 posts with most comments
     *
     * @return List of Post instances that have most comments (top 3)
     */
    public List<Post> getPopularPosts() {
        List<Post> popularPosts = posts;
        Collections.sort(popularPosts, new Comparator<Post>() {
            public int compare(Post o1, Post o2) {
                return o1.getComments().size() > o2.getComments().size() ? -1 : 1;
            }
        });
        if (popularPosts.size() < 3) {
            return popularPosts.subList(0, popularPosts.size());
        }
        return popularPosts.subList(0, 3);
    }

    /**
     * Find posts that were created last
     *
     * @return List of 3 newest posts
     */
    public List<Post> getLatestPosts() {
        List<Post> latestPosts = posts;
        Collections.sort(latestPosts, new Comparator<Post>() {
            public int compare(Post o1, Post o2) {
                return o1.getPostTime().after(o2.getPostTime()) ? -1 : 1;
            }
        });
        if (latestPosts.size() < 3) {
            return latestPosts.subList(0, latestPosts.size());
        }
        return latestPosts.subList(0, 3);
    }

    /**
     * Find last comment made on a post
     *
     * @return Last Post comment as string
     */
    public String getLastPostComment() {
        return getLatestComments().get(0).getCommentContent();
    }

    /**
     * Find a list of recent comments on a post
     *
     * @return List of 3 recent comments
     */
    public List<Comment> getLatestComments() {
        List<Comment> latestComments = post.getComments();
        if (latestComments == null) {
            return new ArrayList<>();
        }
        Collections.sort(latestComments, new Comparator<Comment>() {
            public int compare(Comment o1, Comment o2) {
                return o1.getCommentTime().after(o2.getCommentTime()) ? -1 : 1;
            }
        });
        if (latestComments.size() < 3) {
            return latestComments.subList(0, latestComments.size());
        }
        return latestComments.subList(0, 3);
    }

    /**
     * Event triggered when post gets selected, creates ajax response
     *
     * @param id
     */
    public void onSelectPost(Integer id) {
        postSelected = true;
        post = postDao.findPostById(id);
        response.addRender(commentZone);
    }

    void onValidateFromAddCommentForm() {
        if (commentContent == null) {
            return;
        }
    }

    @CommitAfter
    void onPostComment(Integer id) {
        System.out.println("ACTIVE POST: " + post.getPostTitle());
        System.out.println("COMMENT CONTENT: " + commentContent);

        Comment newComment = new Comment(commentContent, post, loggedInMember);
        post.getComments().add(newComment);

        postDao.saveComment(newComment);
        postDao.updatePost(post);
    }

    void onActivate() {
        posts = postDao.findAllPosts();
    }
}
