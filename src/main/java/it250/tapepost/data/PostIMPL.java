/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.data;

import it250.tapepost.entities.Comment;
import it250.tapepost.entities.Member;
import it250.tapepost.entities.Post;
import java.util.ArrayList;
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

    
    /**
     *
     * @param commentList
     */
    @Override
    public void deleteAllComments(List<Comment> commentList) {
        for (Comment comment : commentList) {
            dbs.delete(comment);
        }
    }

    /**
     *
     * @param postList
     */
    @Override
    public void deleteAllPosts(List<Post> postList) {
        for (Post post : postList) {
            dbs.delete(post);
        }
    }

    /**
     *
     * @param comment
     */
    @Override
    public void deleteComment(Comment comment) {
        dbs.delete(comment);
    }

    /**
     *
     * @param id
     */
    @Override
    public void deletePost(Integer id) {
        Post post = findPostById(id);
        dbs.delete(post);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Comment findCommentById(Integer id) {
        return (Comment) dbs.createCriteria(Comment.class)
                .add(Restrictions.eq("commentId", id))
                .uniqueResult();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Post findPostById(Integer id) {
        return (Post) dbs.createCriteria(Post.class)
                .add(Restrictions.eq("postId", id))
                .uniqueResult();
    }

    /**
     *
     * @param member
     * @return
     */
    @Override
    public List<Post> findPostsByMember(Member member) {
        Integer memberId = member.getMemberId();
        List<Post> memberPosts = dbs.createCriteria(Post.class).add(Restrictions.eq("member", member)).list();
        return memberPosts;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Comment> findAllComments() {
        List<Comment> comments = dbs.createCriteria(Comment.class).list();
        return comments;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Post> findAllPosts() {
        return dbs.createCriteria(Post.class).list();
    }

    /**
     *
     * @param comment
     */
    @Override
    public void saveComment(Comment comment) {
        dbs.persist(comment);
    }

    /**
     *
     * @param post
     */
    @Override
    public void savePost(Post post) {
        dbs.persist(post);
    }

    /**
     *
     * @param comment
     */
    @Override
    public void updateComment(Comment comment) {
        dbs.merge(comment);
    }

    /**
     *
     * @param post
     */
    @Override
    public void updatePost(Post post) {
        dbs.merge(post);
    }
}
