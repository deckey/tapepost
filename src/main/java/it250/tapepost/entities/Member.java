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

    @Inject
    public Member() {
    }

    public Member(String memberFullName, String memberUsername, String memberPassword, MemberRole memberRole) {
        this.memberFullName = memberFullName;
        this.memberUsername = memberUsername;
        this.memberPassword = memberPassword;
        this.memberRole = memberRole;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberFullName() {
        return memberFullName;
    }

    public void setMemberFullName(String memberFullName) {
        this.memberFullName = memberFullName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberCountry() {
        return memberCountry;
    }

    public void setMemberCountry(String memberCountry) {
        this.memberCountry = memberCountry;
    }

    public String getMemberBio() {
        return memberBio;
    }

    public void setMemberBio(String memberBio) {
        this.memberBio = memberBio;
    }

    public Date getMemberJoined() {
        return memberJoined;
    }

    public void setMemberJoined(Date memberJoined) {
        this.memberJoined = memberJoined;
    }

    public MemberRole getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
