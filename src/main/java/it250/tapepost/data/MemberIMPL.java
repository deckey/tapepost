/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.data;

import it250.tapepost.entities.Member;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class MemberIMPL implements MemberDAO {

    @Inject
    private Session dbs;

    @Override
    public boolean checkIfMemberExists(String uName) {
        List<Member> members = findAllMembers();
        for(Member member:members){
            if(member.getMemberUsername().equals(uName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteMember(Integer id) {
        Member member = findMemberById(id);
        dbs.delete(member);
    }

    @Override
    public List<Member> findAllMembers() {
        List< Member> members = dbs.createCriteria(Member.class).list();
        return members;
    }

    @Override
    public Member findMemberById(Integer id) {
        return (Member) dbs.createCriteria(Member.class)
                .add(Restrictions.eq("memberId", id))
                .uniqueResult();
    }

    @Override
    public Member findMemberByUsername(String uName) {
        return (Member) dbs.createCriteria(Member.class)
                .add(Restrictions.eq("memberUsername", uName))
                .uniqueResult();
    }

    @Override
    public Member findMemberByFullName(String fName) {
        return (Member) dbs.createCriteria(Member.class)
                .add(Restrictions.eq("memberFullName", fName))
                .uniqueResult();
    }

    @Override
    public void saveMember(Member member) {
        dbs.persist(member);
    }

    @Override
    public void updateMember(Member member) {
        dbs.merge(member);
    }

    @Override
    public boolean validateMember(String uName, String pWord) {
        Member member = (Member) dbs.createCriteria(Member.class)
                .add(Restrictions.eq("memberUsername", uName))
                .add(Restrictions.eq("memberPassword", pWord))
                .uniqueResult();
        return member != null;
    }
}
