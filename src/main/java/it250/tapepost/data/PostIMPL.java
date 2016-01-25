/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.data;

import it250.tapepost.entities.Member;
import it250.tapepost.entities.Post;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class PostIMPL implements PostDAO {

    @Inject
    private Session dbs;

    @Override
    public void deletePost(Integer id) {
        Post post = findPostById(id);
        dbs.delete(post);
    }
    @Override
    public Post findPostById(Integer id) {
        return (Post) dbs.createCriteria(Post.class)
                .add(Restrictions.eq("postId", id))
                .uniqueResult();
    }

    @Override
    public List<Post> findAllPosts() {
        return dbs.createCriteria(Post.class).list();
    }

    @Override
    public void savePost(Post post) {
        dbs.persist(post);
    }

    @Override
    public void updatePost(Post post) {
        dbs.merge(post);
    }
}
