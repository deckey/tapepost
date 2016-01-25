package it250.tapepost.pages.view;

import it250.tapepost.data.MemberDAO;
import it250.tapepost.entities.Member;
import it250.tapepost.pages.AdminArea;
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
public class ViewMember {
    
   @Inject
    private MemberDAO memberDao;
    
    @Property
    @Persist
    private Member member;
    
    public void set(Member member){
        this.member = member;
    }
    
    void onActivate(Member member){
        this.member = member;
    }
    
    Member onPassivate(){
        return member;
    }
}
