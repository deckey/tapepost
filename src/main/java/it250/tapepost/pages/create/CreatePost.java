/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.pages.create;

import it250.tapepost.data.PostDAO;
import it250.tapepost.entities.Member;
import it250.tapepost.entities.Post;
import it250.tapepost.pages.Posts;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class CreatePost {

    @Property
    @SessionState
    private Member loggedInMember;
    @Property
    @Validate("required")
    private String postTitle;
    @Property
    @Validate("required")
    private String postContent;
    @Property
    private Post post;
    
    @InjectPage
    private Posts postsPage;
    
    @Inject
    private PostDAO postDao;
    
    @CommitAfter
    Object onSuccessFromAddPostForm(){
        Post newPost = new Post(postTitle, postContent, loggedInMember);
        postDao.savePost(newPost);
        return postsPage;
    }
}
