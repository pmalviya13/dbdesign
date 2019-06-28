package com.dbdesign.service;

import com.dbdesign.dao.MemberDAO;
import com.dbdesign.dto.Member;
import com.dbdesign.exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberDAO memberDAO;
    public Member addMember(Member member) throws DAOException
    {
        if(memberDAO.findByEmailId(member.getEmailId())!=null)throw new DAOException("member already exists");
        return memberDAO.save(member);
    }
    public void deleteMember(int id) throws DAOException
    {
        if(memberDAO.getOne(id)==null) throw new DAOException("member not exists");
        memberDAO.deleteById(id);
    }
    public Member updateMember(Member member) throws DAOException
    {
        //if(memberDAO.getOne(member.getCode())==null)throw new DAOException("member not exists");
        //return memberDAO.save(member);
        return memberDAO.findById(member.getCode()).map(member1 ->
                {
                    member1.setFirstName(member.getFirstName());
                    member1.setLastName(member.getLastName());
                    member1.setEmailId(member.getEmailId());
                    member1.setMobileNumber(member.getMobileNumber());
                    return memberDAO.save(member1);
                }).orElseThrow(()-> new DAOException("member not exists"));
    }
    public List<Member> getAllMembers()
    {
        return memberDAO.findAll();
    }
    public Member getMemberById(Integer id) throws DAOException
    {
        return memberDAO.findById(id).orElseThrow(()-> new DAOException("member not found"));
    }
}
