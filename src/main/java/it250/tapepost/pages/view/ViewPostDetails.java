/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.pages.view;

import it250.tapepost.data.PostDAO;
import it250.tapepost.entities.Post;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class ViewPostDetails {

    @Inject
    private PostDAO postDao;

    @Property
    private Post post;

    @Property
    private List<Post> posts;

    @Property
    private String commentContent;

    void onActivate(Integer id) {
        this.post = postDao.findPostById(id);
    }

    public void set(Integer id) {
        this.post = postDao.findPostById(id);
    }

    Post onPassivate() {
        return this.post;
    }

}
