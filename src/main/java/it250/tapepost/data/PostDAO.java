/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.data;

import it250.tapepost.entities.Comment;
import it250.tapepost.entities.Member;
import it250.tapepost.entities.Post;
import java.util.List;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public interface PostDAO {

       /**
     * Delete all comments from the given list of comments
     *
     * @param commentList
     */
    public void deleteAllComments(List<Comment> commentList);

    /**
     * Delete all posts from the given list of posts
     *
     * @param postList
     */
    public void deleteAllPosts(List<Post> postList);

    /**
     * Delete given Comment from the DB
     *
     * @param comment
     */
    public void deleteComment(Comment comment);

    /**
     * Delete given Post from the DB based on Id
     *
     * @param id
     */
    public void deletePost(Integer id);

    /**
     * Find all comments from the DB
     *
     * @return Complete list of all Comment instances
     */
    public List<Comment> findAllComments();

    /**
     * Find all instances of Post entity
     *
     * @return Complete list of all Post instances
     */
    public List<Post> findAllPosts();

    /**
     * Find Comment from DB by specified Id
     *
     * @param id
     * @return Comment object
     */
    public Comment findCommentById(Integer id);

    /**
     * Find Post from DB by specified Id
     *
     * @param id
     * @return Post object
     */
    public Post findPostById(Integer id);

    /**
     * Find all posts from a given Member
     *
     * @param member
     * @return List of posts from a specified Member instance
     */
    public List<Post> findPostsByMember(Member member);

    /**
     * Store Comment instance to DB
     *
     * @param comment
     */
    public void saveComment(Comment comment);

    /**
     * Store Post instance to DB
     *
     * @param post
     */
    public void savePost(Post post);

    /**
     * Update (merge) existing Comment instance to DB
     *
     * @param comment
     */
    public void updateComment(Comment comment);

    /**
     * Update (merge) existsing Post instance to DB
     *
     * @param post
     */
    public void updatePost(Post post);
}
