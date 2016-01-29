package it250.tapepost.pages.edit;

import it250.tapepost.data.MemberDAO;
import it250.tapepost.data.PostDAO;
import it250.tapepost.entities.Comment;
import it250.tapepost.entities.Member;
import it250.tapepost.entities.Post;
import it250.tapepost.pages.AdminArea;
import it250.tapepost.pages.view.ViewMember;
import it250.tapepost.prop.MemberRole;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class EditMember {

    @Property
    private List<Comment> comments;

    @SessionState
    private Member loggedInMember;

    /* FIELDS */
    @Property
    private String memberBio;
    @Property
    private String memberCountry;
    @Property
    private String memberEmail;
    @Property
    private String memberFullName;
    @Property
    private String memberUsername;
    @Property
    private String memberPassword;
    @Property
    private String passwordFormat;
    @Property
    private String repeatPassword = "";
    @InjectComponent("memberEditForm")
    private Form memberEditForm;

    @Inject
    private MemberDAO memberDao;

    @Property
    @Persist
    private Member member;
    @Property
    private List<Member> members;

    @Inject
    private PostDAO postDao;
    @InjectPage
    private ViewMember viewMemberPage;

    public boolean checkPassword(String pass1, String pass2) {
        return pass1.equals(pass2);
    }

    public String getFormattedDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
        if (dateFormat.format(date).equals(dateFormat.format(new Date()))) {
            return "today";
        }
        return dateFormat.format(date);
    }

    public boolean getLoggedInRole() {
        if ((loggedInMember.getMemberRole().equals(MemberRole.Administrator))
                || (loggedInMember.getMemberId() == member.getMemberId())) {
            return true;
        }
        return false;
    }

    /* Form validation */
    @CommitAfter
    Object onSuccessFromMemberEditForm() {
        if (!checkPassword(memberPassword, repeatPassword)) {
            memberEditForm.recordError("Passwords don't match!");
            return null;
        }
        memberDao.updateMember(member);
        viewMemberPage.set(member);
        return viewMemberPage;
    }

    public MemberRole[] getRoles() {
        MemberRole[] roles = MemberRole.values();
        return roles;
    }

    public boolean getUserAdmin() {
        return loggedInMember.getMemberRole().equals(MemberRole.Administrator);
    }

    public void set(Member member) {
        this.member = member;
    }

    //MAKE SURE ONLY ADMINS OR PAGE OWNERS CAN ACCESS
    Object onActivate(Member member) {
        this.member = member;
        members = memberDao.findAllMembers();
        passwordFormat = member.getMemberPassword().replaceAll(".", "*");
        if (loggedInMember.getMemberRole().equals(MemberRole.Administrator)) {
            return null;
        } else if (loggedInMember.getMemberId() == this.member.getMemberId()) {
            return null;
        } else {
            viewMemberPage.set(member);
            return viewMemberPage;
        }
    }

    Member onPassivate() {
        return member;
    }

    @CommitAfter
    Object onDeleteMember(Integer id) {

//        List<Post> memberPosts = postDao.findPostsByMember(member);
//        List<Comment> memberComments = new ArrayList<>();
//        for (Post post : memberPosts) {
//            for (Comment comment : post.getComments()) {
//                System.out.println("MEMBER COMMENT: " + comment.getCommentContent());
//                postDao.deleteComment(comment);
//            }
//        }
        Member member = memberDao.findMemberById(id);
        for (Post post : member.getPosts()) {
            for (Comment comment : post.getComments()) {
                postDao.deleteComment(comment);
            }
        }
        memberDao.deleteMember(id);
        return AdminArea.class;
    }
}
