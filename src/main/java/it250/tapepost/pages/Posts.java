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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class Posts {
    @Property
    private Comment comment;

    @Property
    private List<Comment> comments;

    @Property
    @SessionState
    private Member loggedInMember;
    @Property
    private Member member;
    @Inject
    private MemberDAO memberDao;
    @Inject
    private PostDAO postDao;

    @Property
    private Post post;

    @Property
    private List<Post> posts;

    private List<Post> popularPosts;
    private List<Comment> recentComments;
    private List<Post> recentPosts;

    @InjectComponent
    private Zone postDetailsZone;

    @Property
    private boolean postSelected;

    @Inject
    private AjaxResponseRenderer response;

    public String getFormattedDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
        return dateFormat.format(date);
    }

    public String getFormattedTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }

    public List<Post> getPopularPosts() {
        List<Post> allPosts = postDao.findAllPosts();
        Collections.sort(allPosts, new Comparator<Post>() {
            public int compare(Post p1, Post p2) {
                return p2.getComments().size() > (p1.getComments().size()) ? 1 : -1;
            }
        });
        if (allPosts == null) {
            allPosts = new ArrayList<>();
        }

        // if less than 3 posts, display them all
        if (allPosts.size() < 3) {
            popularPosts = allPosts.subList(0, allPosts.size());
        } else {
            // otherwise, get top 3  
            popularPosts = allPosts.subList(0, 3);
        }
        return popularPosts;
    }

    public List<Post> getRecentPosts() {
        List<Post> allPosts = postDao.findAllPosts();
        if (allPosts == null) {
            allPosts = new ArrayList<>();
        }
        // compare posts by creation date
        Collections.sort(allPosts, new Comparator<Post>() {
            public int compare(Post p1, Post p2) {
                return p2.getPostTime().compareTo(p1.getPostTime());
            }
        });

        // if less than 3 posts, display them all
        if (allPosts.size() < 3) {
            recentPosts = allPosts.subList(0, allPosts.size());
        } else {
            // otherwise, get top 3  
            recentPosts = allPosts.subList(0, 3);
        }
        return recentPosts;
    }

    public List<Comment> getRecentComments() {
        List<Comment> allComments = new ArrayList<>(post.getComments());
        if (allComments == null) {
            allComments = new ArrayList<>();
        }
        // compare posts by creation date
        Collections.sort(allComments, new Comparator<Comment>() {
            public int compare(Comment c1, Comment c2) {
                return c2.getCommentTime().compareTo(c1.getCommentTime());
            }
        });

        // if less than 3 posts, display them all
        if (allComments.size() < 3) {
            recentComments = allComments.subList(0, allComments.size());
        } else {
            // otherwise, get top 3  
            recentComments = allComments.subList(0, 3);
        }
        return recentComments;
    }

    public void onSelectPost(Integer id) {
        post = postDao.findPostById(id);
        postSelected = true;
        comments = new ArrayList<>(post.getComments());
        response.addRender(postDetailsZone);
    }
    public String getCommentMember(Integer id){
        Comment comm = postDao.findCommentById(id);
        return memberDao.findMemberById(comm.getCommentMemberId()).getMemberUsername();
    }

    @CommitAfter
    public void onPostComment(Integer id) {
        post = postDao.findPostById(id);
        postSelected = true;
        Comment comment1 = new Comment("This is an auto-generated comment!", post);
        comment1.setCommentMemberId(loggedInMember.getMemberId());
        comment1.setPost(post);
        post.getComments().add(comment1);

        postDao.saveComment(comment1);
        postDao.updatePost(post);
        comments = new ArrayList<>(post.getComments());
        response.addRender(postDetailsZone);
    }

    void onActivate() {
        if (post == null) {
            post = new Post();
        }
        if (posts == null) {
            posts = new ArrayList<>();
        }
        if (recentPosts == null) {
            recentPosts = new ArrayList<>();
        }
        if (popularPosts == null) {
            popularPosts = new ArrayList<>();
        }
        posts = postDao.findAllPosts();
    }
}
