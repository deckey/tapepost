package it250.tapepost.pages.delete;

import it250.tapepost.data.MemberDAO;
import it250.tapepost.data.PostDAO;
import it250.tapepost.entities.Comment;
import it250.tapepost.entities.Member;
import it250.tapepost.entities.Post;
import it250.tapepost.pages.AdminArea;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
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
public class DeleteMember {

    @Property
    private List<Comment> comments;

    @Inject
    private MemberDAO memberDao;

    @Property
    @Persist
    private Member member;

    @Inject
    private PostDAO postDao;

    @Property
    private List<Post> posts;

    @Property
    private List<Post> memberPosts;

    public List<Comment> getMemberComments() {
        List<Comment> memberComments = new ArrayList<>();
        comments = postDao.findAllComments();
        for (Comment comment : comments) {
            if (comment.getCommentMemberId().equals(member.getMemberId())) {
                memberComments.add(comment);
            }
        }
        return memberComments;
    }

    public void set(Member member) {
        this.member = member;
    }

    void onActivate(Member member) {
        this.member = member;
        memberPosts = member.getPosts();
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
