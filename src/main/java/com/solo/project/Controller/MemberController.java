package com.solo.project.Controller;

import com.solo.project.Mapper.MemberMapper;
import com.solo.project.Service.MemberService;
import com.solo.project.Entity.Member;
import com.solo.project.Dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberMapper mapper;


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/join")
    public ResponseEntity<Member> join(@RequestBody MemberDto.Post memberPostDto){
        Member member = mapper.memberPostDtoToMember(memberPostDto);
        return new ResponseEntity<>(memberService.join(member) ,HttpStatus.CREATED);
    }

    @GetMapping("/allMember")
    public ResponseEntity<List<Member>> getMemberList() {
        return ResponseEntity.ok(memberService.memberList());
    }

    @GetMapping("/Type/{CompanyType}")
    public ResponseEntity<List<Member>> getCompanyType(@PathVariable Long CompanyType) {
        return new ResponseEntity<>(memberService.findAllCompanyType(CompanyType), HttpStatus.OK);
    }

    @GetMapping("/Location/{CompanyLocation}")
    public ResponseEntity<List<Member>> getCompanyLocation(@PathVariable Long CompanyLocation) {
        return new ResponseEntity<>(memberService.findAllCompanyLocation(CompanyLocation), HttpStatus.OK);
    }
}
