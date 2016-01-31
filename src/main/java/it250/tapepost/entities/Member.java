/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.entities;

import it250.tapepost.prop.MemberRole;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "memberId")
    private Integer memberId;

    @Column(name = "memberFullName")
    private String memberFullName;

    @Column(name = "memberEmail")
    private String memberEmail;

    @Column(name = "memberUsername")
    private String memberUsername;

    @Column(name = "memberPassword")
    private String memberPassword;

    @Column(name = "memberCountry")
    private String memberCountry;

    @Column(name = "memberBio")
    private String memberBio;

    @Column(name = "memberJoined")
    @Temporal(TemporalType.TIMESTAMP)
    private Date memberJoined;

    @Column(name = "memberRole")
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> posts;

    /**
     * Empty constructor
     */
    @Inject
    public Member() {
    }

    /**
     *
     * @param memberFullName
     * @param memberUsername
     * @param memberPassword
     * @param memberRole
     */
    public Member(String memberFullName, String memberUsername, String memberPassword, MemberRole memberRole) {
        this.memberFullName = memberFullName;
        this.memberUsername = memberUsername;
        this.memberPassword = memberPassword;
        this.memberRole = memberRole;
    }

    /**
     *
     * @return
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     *
     * @param memberId
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     *
     * @return
     */
    public String getMemberFullName() {
        return memberFullName;
    }

    /**
     *
     * @param memberFullName
     */
    public void setMemberFullName(String memberFullName) {
        this.memberFullName = memberFullName;
    }

    /**
     *
     * @return
     */
    public String getMemberEmail() {
        return memberEmail;
    }

    /**
     *
     * @param memberEmail
     */
    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    /**
     *
     * @return
     */
    public String getMemberUsername() {
        return memberUsername;
    }

    /**
     *
     * @param memberUsername
     */
    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    /**
     *
     * @return
     */
    public String getMemberPassword() {
        return memberPassword;
    }

    /**
     *
     * @param memberPassword
     */
    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    /**
     *
     * @return
     */
    public String getMemberCountry() {
        return memberCountry;
    }

    /**
     *
     * @param memberCountry
     */
    public void setMemberCountry(String memberCountry) {
        this.memberCountry = memberCountry;
    }

    /**
     *
     * @return
     */
    public String getMemberBio() {
        return memberBio;
    }

    /**
     *
     * @param memberBio
     */
    public void setMemberBio(String memberBio) {
        this.memberBio = memberBio;
    }

    /**
     *
     * @return
     */
    public Date getMemberJoined() {
        return memberJoined;
    }

    /**
     *
     * @param memberJoined
     */
    public void setMemberJoined(Date memberJoined) {
        this.memberJoined = memberJoined;
    }

    /**
     *
     * @return
     */
    public MemberRole getMemberRole() {
        return memberRole;
    }

    /**
     *
     * @param memberRole
     */
    public void setMemberRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }

    /**
     *
     * @return
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     *
     * @param posts
     */
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return this.memberUsername;
    }
    
    
}
