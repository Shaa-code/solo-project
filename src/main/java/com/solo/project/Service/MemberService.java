package com.solo.project.Service;

import com.solo.project.Entity.Member;
import com.solo.project.Repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(Member member){ return memberRepository.save(member); }
    // 전체 회원조회
    public List<Member> memberList(){
        return memberRepository.findAll();
    }

    // 업종에 맞게 조회
    public List<Member> findAllCompanyType(Long number){
        return memberRepository.findByCompanyType(number);
    }

    // 지역에 맞게 조회
    public List<Member> findAllCompanyLocation(Long number){
        return memberRepository.findByCompanyLocation(number);
    }

}
