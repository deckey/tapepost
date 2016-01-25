/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.pages;

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
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class Posts {

    @Property
    private List<Comment> comments;

    @Property
    private Member member;
    @Inject
    private PostDAO postDao;

    @Property
    private Post post;

    @Property
    private List<Post> posts;
    
    private List<Post> recentPosts;
    @InjectComponent
    private Zone postDetailsZone;
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

    public List<Post> getRecentPosts() {
        List<Post> allPosts = postDao.findAllPosts();
        if (allPosts==null){
            allPosts= new ArrayList<>();
        }
        // compare projects by creation date
        Collections.sort(allPosts, new Comparator<Post>() {
            public int compare(Post p1, Post p2) {
                return p2.getPostTime().compareTo(p1.getPostTime());
            }
        });

        // if less than 3 posts, display them all
        if (allPosts.size() < 3) {
            recentPosts = allPosts.subList(0, allPosts.size());
        } else {
            // otherwise, get top 5
            recentPosts = allPosts.subList(0, 3);
        }
        return recentPosts;
    }

    public void onSelectPost(Integer id) {
        post = postDao.findPostById(id);
        comments = new ArrayList<>(post.getComments());
        Collections.sort(comments);
        response.addRender(postDetailsZone);
    }

    void onActivate() {
        if(post==null){
            post = new Post();
        }
        if (posts == null) {
            posts = new ArrayList<>();
        }
        if(recentPosts==null){
            recentPosts = new ArrayList<>();
        }
        posts = postDao.findAllPosts();
    }
}
