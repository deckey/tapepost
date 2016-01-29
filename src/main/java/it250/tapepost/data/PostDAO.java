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
    
    public void deleteAllComments(List<Comment> commentList);
    
    public void deleteAllPosts(List<Post> postList);
    
    
    public void deleteComment(Comment comment);

    public void deletePost(Integer id);

    public List<Comment> findAllComments();

    public List<Post> findAllPosts();

    public Comment findCommentById(Integer id);

    public Post findPostById(Integer id);

    public List<Post> findPostsByMember(Member member);

    public void saveComment(Comment comment);

    public void savePost(Post post);

    public void updateComment(Comment comment);

    public void updatePost(Post post);
}
