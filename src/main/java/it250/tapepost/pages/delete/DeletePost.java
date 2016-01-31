/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.pages.delete;

import it250.tapepost.data.PostDAO;
import it250.tapepost.entities.Member;
import it250.tapepost.entities.Post;
import it250.tapepost.pages.AdminArea;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class DeletePost {

    @Inject
    private PostDAO postDao;

    @Property
    private Post post;

    /**
     * Page activation context method to display a post selected from a different page
     * @param post
     */
    public void set(Post post) {
        this.post = post;
    }

    void onActivate(Post post) {
        this.post = post;
    }

    Post onPassivate() {
        return post;
    }

    @CommitAfter
    Object onDeletePost(Integer id) {
        postDao.deletePost(id);
        return AdminArea.class;
    }
}
