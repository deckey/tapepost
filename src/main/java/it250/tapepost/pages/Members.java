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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class Members {

    @Property
    private List<Comment> comments;

    @Property
    private Comment comment;

    @Inject
    private MemberDAO memberDao;

    @Property
    private Member member;

    @Property
    private List<Member> members;

    @Inject
    private PostDAO postDao;

    @Property
    private Post post;

    @Property
    private List<Post> posts;

    public List<Comment> getMemberComments(Member member) {
        List<Comment> memberComments = new ArrayList<>();
        comments = postDao.findAllComments();
        for (Comment comment : comments) {
            if (comment.getCommentMemberId().equals(member.getMemberId())) {
                memberComments.add(comment);
            }
        }
        return memberComments;
    }

    public String getFormattedDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
        if (dateFormat.format(date).equals(dateFormat.format(new Date()))) {
            return "today";
        }
        return dateFormat.format(date);
    }

    public String getFormattedTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }

    public String getCommentingMember(Integer id) {
        return memberDao.findMemberById(id).getMemberUsername();
    }

    public List<Post> getPopularPosts() {
        List<Post> popularPosts = posts;
        Collections.sort(popularPosts, new Comparator<Post>() {
            public int compare(Post o1, Post o2) {
                return o1.getComments().size() > o2.getComments().size() ? -1 : 1;
            }
        });
        if (popularPosts.size() < 5) {
            return popularPosts.subList(0, popularPosts.size());
        }
        return popularPosts.subList(0, 5);
    }

    void onActivate() {
        members = memberDao.findAllMembers();
        posts = postDao.findAllPosts();
    }

}
