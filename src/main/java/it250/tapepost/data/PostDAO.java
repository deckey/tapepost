/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.data;

import it250.tapepost.entities.Member;
import it250.tapepost.entities.Post;
import java.util.List;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public interface PostDAO {

    public void deletePost(Integer id);

    public List<Post> findAllPosts();

    public Post findPostById(Integer id);

    public void savePost(Post post);

    public void updatePost(Post post);
}
