/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it250.tapepost.data;

import it250.tapepost.entities.Member;
import java.util.List;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public interface MemberDAO {
    
    public boolean checkIfMemberExists(String uName);

    public void deleteMember(Integer id);

    public List<Member> findAllMembers();

    public Member findMemberById(Integer id);

    public Member findMemberByUsername(String uName);

    public Member findMemberByFullName(String fName);

    public void saveMember(Member member);

    public void updateMember(Member member);

    public boolean validateMember(String uName, String pWord);
}
