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

    /**
     * Find Member instance with highest number of posts (most active member)
     *
     * @return Member object that has highest number of posts
     */
    public Member getMostActiveMember() {
        List<Member> activeMembers = members;
//Sort the list of all members based on their post count, then take the first one
        Collections.sort(activeMembers, new Comparator<Member>() {
            @Override
            public int compare(Member mem1, Member mem2) {
                return (mem1.getPosts().size() > (mem2.getPosts().size())) ? -1 : 1;
            }
        });
        return activeMembers.get(0);
    }

    /**
     * Find last registered member
     *
     * @return Member object
     */
    public Member getLatestMember() {
        List<Member> latestMembers = memberDao.findAllMembers();
        List<Member> sortedMembers = latestMembers;
//Get a full list of members and sort it based on their 'join' date, then return the first one
        Collections.sort(sortedMembers, new Comparator<Member>() {
            @Override
            public int compare(Member client1, Member client2) {
                return (client1.getMemberJoined().after(client2.getMemberJoined())) ? -1 : 1;
            }
        });
        return sortedMembers.get(0);
    }

    /**
     * Find all comments from a specified Member
     *
     * @param member
     * @return List of comments that specified Member created
     */
    public List<Comment> getMemberComments(Member member) {
        List<Comment> memberComments = new ArrayList<>();
        // create an empty list for member comments
        List<Comment> comments = postDao.findAllComments();
        for (Comment comment : comments) {
            // iterating through all comments, check if any are created by a member and add to the list
            if (comment.getCommentMemberId().equals(member.getMemberId())) {
                memberComments.add(comment);
            }
        }
        return memberComments;
    }

    /**
     * Find last post
     *
     * @return
     */
    public Post getLatestPost() {
        List<Post> latestPosts = posts;
//Get a full list of posts and sort it based on their 'creation' date, then return the first one
        Collections.sort(latestPosts, new Comparator<Post>() {
            public int compare(Post o1, Post o2) {
                return o1.getPostTime().after(o2.getPostTime()) ? -1 : 1;
            }
        });
        return latestPosts.get(0);
    }

    /**
     * Find Member who has the largest number of comments
     *
     * @return Member object with largest number of comments
     */
    public Member getMostActiveCommenter() {
        List<Member> activeMembers = members;
//Get a full list of members and sort it based on the number of their comments, then return the first one
        Collections.sort(activeMembers, new Comparator<Member>() {
            @Override
            public int compare(Member mem1, Member mem2) {
                return (getMemberComments(mem1).size() > (getMemberComments(mem2).size())) ? -1 : 1;
            }
        });
        return activeMembers.get(0);
    }
    
    /**
     * Return specified date in a readable format
     * @param date
     * @return Date as a string, e.g. Jan 09
     */
    public String getFormattedDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
        if (dateFormat.format(date).equals(dateFormat.format(new Date()))) {
            return "today";
        }
        return dateFormat.format(date);
    }
    
    /**
     *
     * Return specified time in a readable format
     * @param date
     * @return Date as a time string, e.g. 12:15
     */
    public String getFormattedTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }

    /**
     * Find a member who made a specified comment
     * @param id
     * @return Member's username as a string
     */
    public String getCommentingMember(Integer id) {
        return memberDao.findMemberById(id).getMemberUsername();
    }

    

    /**
     * Find Post who has the largest number of comments
     *
     * @return
     */
    public Post getPopularPost() {
        List<Post> popularPosts = posts;
//Get a full list of posts and sort it based on the number of their comments, then return the first one
        Collections.sort(popularPosts, new Comparator<Post>() {
            public int compare(Post o1, Post o2) {
                return o1.getComments().size() > o2.getComments().size() ? -1 : 1;
            }
        });
        return popularPosts.get(0);
    }

    void onActivate() {
        members = (memberDao.findAllMembers() == null) ? new ArrayList<>() : memberDao.findAllMembers();
        posts = postDao.findAllPosts();
        if (posts == null) {
            posts = new ArrayList<>();
        }
    }

}
