package com.solo.project.Mapper;

import com.solo.project.Entity.Member;
import com.solo.project.Dto.MemberDto;
import org.springframework.stereotype.Component;



//@Mapper(componentModel = "spring")
//public interface MemberMapper{
//    Member memberPostDtoToMember(MemberDto.Post memberPostDto);
//}

@Component
public class MemberMapper {
    public Member memberPostDtoToMember(MemberDto.Post memberPostDto){
        return new Member(0L,
                memberPostDto.getName(),
                memberPostDto.getPassword(),
                memberPostDto.getSex(),
                memberPostDto.getCompanyName(),
                memberPostDto.getCompanyType(),
                memberPostDto.getCompanyLocation());
    }
}
