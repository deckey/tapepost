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

    /**
     * Check if member exists in the DB based on given username
     * @param uName
     * @return True if member exists
     */
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

    /**
     * Delete member from DB based on given Id
     * @param id
     */
    @Override
    public void deleteMember(Integer id) {
        Member member = findMemberById(id);
        dbs.delete(member);
    }

    /**
     * Find all instances of Member entity from DB
     * @return List of member instances
     */
    @Override
    public List<Member> findAllMembers() {
        List< Member> members = dbs.createCriteria(Member.class).list();
        return members;
    }

    /**
     * Find Member instance from DB based on given integer Id
     * @param id
     * @return Member object
     */
    @Override
    public Member findMemberById(Integer id) {
        return (Member) dbs.createCriteria(Member.class)
                .add(Restrictions.eq("memberId", id))
                .uniqueResult();
    }

    /**
     * Find Member instance based on username
     * @param uName
     * @return Member instance
     */
    @Override
    public Member findMemberByUsername(String uName) {
        return (Member) dbs.createCriteria(Member.class)
                .add(Restrictions.eq("memberUsername", uName))
                .uniqueResult();
    }

    /**
     * Find member by given full name
     * @param fName
     * @return Member object 
     */
    @Override
    public Member findMemberByFullName(String fName) {
        return (Member) dbs.createCriteria(Member.class)
                .add(Restrictions.eq("memberFullName", fName))
                .uniqueResult();
    }

    /**
     *
     * @param member
     */
    @Override
    public void saveMember(Member member) {
        dbs.persist(member);
    }

    /**
     *
     * @param member
     */
    @Override
    public void updateMember(Member member) {
        dbs.merge(member);
    }

    /**
     *
     * @param uName
     * @param pWord
     * @return
     */
    @Override
    public boolean validateMember(String uName, String pWord) {
        Member member = (Member) dbs.createCriteria(Member.class)
                .add(Restrictions.eq("memberUsername", uName))
                .add(Restrictions.eq("memberPassword", pWord))
                .uniqueResult();
        return member != null;
    }
}
