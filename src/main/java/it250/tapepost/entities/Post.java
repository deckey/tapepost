/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Entity
@Table(name = "post")
public class Post implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "postId")
    private Integer postId;

    @Column(name = "postTitle")
    private String postTitle;

    @Column(name = "postContent")
    private String postContent;

    @Column(name = "postTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postTime;

    @ManyToOne
    @JoinColumn(name = "postMemberId")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    /**
     * Empty constructor
     */
    @Inject
    public Post() {
    }

    /**
     *
     * @param postTitle
     * @param postContent
     * @param member
     */
    public Post(String postTitle, String postContent,  Member member) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.member = member;
    }

    /**
     *
     * @return
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     *
     * @param postId
     */
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    /**
     *
     * @return
     */
    public String getPostTitle() {
        return postTitle;
    }

    /**
     *
     * @param postTitle
     */
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    /**
     *
     * @return
     */
    public String getPostContent() {
        return postContent;
    }

    /**
     *
     * @param postContent
     */
    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    /**
     *
     * @return
     */
    public Date getPostTime() {
        return postTime;
    }

    /**
     *
     * @param postTime
     */
    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    /**
     *
     * @return
     */
    public Member getMember() {
        return member;
    }

    /**
     *
     * @param member
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     *
     * @return
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
