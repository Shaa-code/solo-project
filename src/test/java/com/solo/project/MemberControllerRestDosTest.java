package com.solo.project;

import com.google.gson.Gson;
import com.solo.project.Controller.MemberController;
import com.solo.project.Dto.MemberDto;
import com.solo.project.Entity.Member;
import com.solo.project.Mapper.MemberMapper;
import com.solo.project.Service.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MemberControllerRestDosTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    public void postMemberTest() throws Exception {

        //given
        MemberDto.Post post = new MemberDto.Post("홍길동", "1234", "m", "Naver", 1L, 1L);
        String content = gson.toJson(post);

        given(mapper.memberPostDtoToMember(Mockito.any(MemberDto.Post.class))).willReturn(new Member());

        //when
        ResultActions actions = mockMvc.perform(post("/join")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        );


        //then
        actions
                .andExpect(status().isCreated())
                .andDo(document(
                        "post-member",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("패스워드"),
                                        fieldWithPath("sex").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("companyName").type(JsonFieldType.STRING).description("회사이름"),
                                        fieldWithPath("companyType").type(JsonFieldType.NUMBER).description("회사업종"),
                                        fieldWithPath("companyLocation").type(JsonFieldType.NUMBER).description("회사지역")
                                )
                        )
                ));

    }

    @Test
    public void getMemberListTest() throws Exception {

        //given
        Member member1 = new Member(1L, "홍길동", "1234", "m", "Naver", 1L, 1L);
        Member member2 = new Member(2L, "홍길똥", "1234", "m", "코드스테이츠", 2L, 1L);

        List<Member> memberList = List.of(member1, member2);

        given(memberService.memberList()).willReturn(memberList);

        //when
        mockMvc.perform(get("/allMember"))
                .andExpect(status().isOk())
                .andDo(document(
                        "allMember",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }

    @Test
    public void getCompanyType() throws Exception {
        //given
        Member member1 = new Member(1L, "홍길동", "1234", "m", "NAVER", 1L, 1L);
        Member member2 = new Member(2L, "홍길똥", "1234", "m", "코드스테이츠", 2L, 1L);
        Member member3 = new Member(3L, "홍삼동", "1234", "m", "카카오", 1L, 3L);

        List<Member> memberList = List.of(member1, member2, member3);

        given(memberService.memberList()).willReturn(memberList);

        //when
        mockMvc.perform(get("/Type/1"))
                .andExpect(status().isOk())
                .andDo(document(
                        "CompanyType",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));

        //수정 필요
    }

    @Test
    public void getLocationType() throws Exception{
        //given
        Member member1 = new Member(1L,"홍길동","1234","m","Naver",1L,1L);
        Member member2 = new Member(2L,"홍길똥","1234","m","코드스테이츠",2L,1L);
        Member member3 = new Member(3L,"홍삼동","1234","m","카카오",1L,3L);

        List<Member> memberList = List.of(member1, member2, member3);

        given(memberService.memberList()).willReturn(memberList);

        //when
        mockMvc.perform(get("/Location/1"))
                .andExpect(status().isOk())
                .andDo(document(
                        "CompanyLocation",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));

        //수정 필요
        }
    }
