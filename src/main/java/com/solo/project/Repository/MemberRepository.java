package com.solo.project.Repository;

import com.solo.project.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByCompanyType(Long number);

    List<Member> findByCompanyLocation(Long number);
}

