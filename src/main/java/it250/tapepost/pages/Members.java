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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.apache.tapestry5.annotations.InjectPage;
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

    @InjectPage
    private Dashboard dashboardPage;

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

    /**
     * Local procedure to call dashboard method to return member's comments
     *
     * @param member
     * @return List of comments specified Member created
     */
    public List<Comment> getMemberComments(Member member) {
        return dashboardPage.getMemberComments(member);
    }

    /**
     * Return specified date in a readable format
     *
     * @param date
     * @return Date as a string, e.g. Jan 09
     */
    public String getFormattedDate(Date date) {
        return dashboardPage.getFormattedDate(date);
    }

    /**
     *
     * Return specified time in a readable format
     *
     * @param date
     * @return Date as a time string, e.g. 12:15
     */
    public String getFormattedTime(Date date) {
        return dashboardPage.getFormattedTime(date);
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
     * Find popular posts, top 5 that have most comments
     *
     * @return List of Post instances
     */
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
