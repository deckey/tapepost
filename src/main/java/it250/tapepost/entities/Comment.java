/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "commentId")
    private Integer commentId;

    @Column(name = "commentContent")
    private String commentContent;

    @Column(name = "commentTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentTime;

    @Column(name = "commentMemberId")
    private Integer commentMemberId;

    @ManyToOne
    @JoinColumn(name = "commentPostId")
    private Post post;

    @Inject
    public Comment() {
    }

    public Comment(String commentContent, Post post, Member member) {
        this.commentContent = commentContent;
        this.post = post;
        this.commentMemberId = member.getMemberId();
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getCommentMemberId() {
        return commentMemberId;
    }

    public void setCommentMemberId(Integer commentMemberId) {
        this.commentMemberId = commentMemberId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
