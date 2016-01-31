/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.pages;

import it250.tapepost.data.MemberDAO;
import it250.tapepost.data.PostDAO;
import it250.tapepost.entities.Comment;
import it250.tapepost.entities.Member;
import it250.tapepost.entities.Post;
import it250.tapepost.prop.MemberRole;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class AdminArea {

    @Property
    private Comment comment;
    
    @Property
    private List<Comment> comments;
    
    
    @SessionState
    private Member loggedInMember;
    @Inject
    private MemberDAO memberDao;

    @Property
    private List<Member> members;

    @Property
    private Member member;

//    FIELDS
    @Property
    private String memberCountry;
    @Property
    @Validate("required")
    private String memberFullName;
    @Property
    @Validate("required")
    private String memberUsername;
    @Property
    @Validate("required")
    private String memberPassword;
    @Property
    @Validate("required")
    private String memberEmail;
    @Property
    private String memberBio;
    @Property
    @Validate("required")
    private MemberRole memberRole;

    @Property
    private Member rowMember;

    @Property
    private Post post;
    @Inject
    private PostDAO postDao;

    @Property
    private List<Post> posts;

    /**
     * Get list of existing roles from a MemberRole entity
     * @return List of roles, Administrator and Member
     */
    public MemberRole[] getRoles() {
        return MemberRole.values();
    }

    void onPrepare() {
        if (members == null) {
            members = new ArrayList<>();
        }
        if (posts == null) {
            posts = new ArrayList<>();
        }
        if(comments==null){
            comments = new ArrayList<>();
        }
    }

    void onActivate() {
        members = memberDao.findAllMembers();
        posts = postDao.findAllPosts();
        comments = postDao.findAllComments();
    }

    @CommitAfter
    void onSuccessFromAddMemberForm() {
        Member newMember = new Member(memberFullName, memberUsername, memberPassword, memberRole);
        newMember.setMemberBio(memberBio);
        newMember.setMemberEmail(memberEmail);
        newMember.setMemberCountry(memberCountry);
        memberDao.saveMember(newMember);
    }
    
    @CommitAfter
    void onDeleteComment(Comment comment){
        postDao.deleteComment(comment);
    }
}
