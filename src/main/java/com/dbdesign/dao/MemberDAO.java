package com.dbdesign.dao;

import com.dbdesign.dto.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDAO extends JpaRepository<Member, Integer> {
    public Member findByEmailId(String emailId);
}
