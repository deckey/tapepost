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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class Dashboard {
    
    @Property
    private List<Comment> comments;
    
    @Property
    @SessionState
    private Member loggedInMember;

    @Property
    private List<Member> members;

    @Inject
    private MemberDAO memberDao;

    @Property
    private List<Post> posts;

    @Inject
    private PostDAO postDao;

    public Member getMostActiveMember() {
        List<Member> activeMembers = members;

        Collections.sort(activeMembers, new Comparator<Member>() {
            @Override
            public int compare(Member mem1, Member mem2) {
                return (mem1.getPosts().size() > (mem2.getPosts().size())) ? -1 : 1;
            }
        });
        return activeMembers.get(0);
    }

    public Member getLatestMember() {
        List<Member> latestMembers = memberDao.findAllMembers();
        List<Member> sortedMembers = latestMembers;

        Collections.sort(sortedMembers, new Comparator<Member>() {
            @Override
            public int compare(Member client1, Member client2) {
                return (client1.getMemberJoined().after(client2.getMemberJoined())) ? -1 : 1;
            }
        });
        return sortedMembers.get(0);
    }

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
    
    public Member getMostActiveCommenter(){
         List<Member> activeMembers = members;

        Collections.sort(activeMembers, new Comparator<Member>() {
            @Override
            public int compare(Member mem1, Member mem2) {
                return (getMemberComments(mem1).size() > (getMemberComments(mem2).size())) ? -1 : 1;
            }
        });
        return activeMembers.get(0);
    }

    public List<Comment> getAllComments() {
        return postDao.findAllComments();
    }

    public List<Post> getAllPosts() {
        return postDao.findAllPosts();
    }

    void onActivate() {
        members = (memberDao.findAllMembers() == null) ? new ArrayList<>() : memberDao.findAllMembers();
        posts = postDao.findAllPosts();
        if (posts == null) {
            posts = new ArrayList<>();
        }
    }

}
