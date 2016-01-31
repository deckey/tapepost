/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.data;

import it250.tapepost.entities.Member;
import java.util.List;

/**
 * Main DAO service for manipulating Member instances
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public interface MemberDAO {
    
    /**
     * Check if a Member with given username exists in a DB
     * @param uName 
     * @return True if member exists
     */
    public boolean checkIfMemberExists(String uName);

    /**
     * Delete Member instance based on it's Id
     * @param id
     */
    public void deleteMember(Integer id);

    /**
     * Get a list of all member instances
     * @return member List list of all members
     */
    public List<Member> findAllMembers();

    /**
     * Find member instance based on a given Id
     * @param id
     * @return unique Member object 
     */
    public Member findMemberById(Integer id);

    /**
     * Find user based on given username
     * @param uName
     * @return unique Member object
     */
    public Member findMemberByUsername(String uName);

    /**
     * Find user based on user's full name
     * @param fName
     * @return  Member object
     */
    public Member findMemberByFullName(String fName);

    /**
     * Store given Member instance to DB
     * @param member
     */
    public void saveMember(Member member);

    /**
     * Update Member instance to DB
     * @param member
     */
    public void updateMember(Member member);

    /**
     * Check (validate) if member username exists in the DB and has the given password
     * @param uName
     * @param pWord
     * @return True if Member exists
     */
    public boolean validateMember(String uName, String pWord);
}
