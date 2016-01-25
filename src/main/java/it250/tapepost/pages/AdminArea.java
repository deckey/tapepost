/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.pages;

import it250.tapepost.data.MemberDAO;
import it250.tapepost.data.PostDAO;
import it250.tapepost.entities.Member;
import it250.tapepost.entities.Post;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class AdminArea {

    @SessionState
    private Member loggedInMember;
    @Inject
    private MemberDAO memberDao;

    @Property
    private List<Member> members;

    @Property
    private Member member;

    @Property
    private Member rowMember;

    @Property
    private Post post;
    @Inject
    private PostDAO postDao;

    @Property
    private List<Post> posts;

    void onPrepare() {
        if (members == null) {
            members = new ArrayList<>();
        }
        if (posts == null) {
            posts = new ArrayList<>();
        }
    }

    void onActivate() {
        members = memberDao.findAllMembers();
        posts = postDao.findAllPosts();
    }

}
