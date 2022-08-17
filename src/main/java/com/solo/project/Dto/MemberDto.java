package com.solo.project.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;


public class MemberDto {
    @Getter
    @AllArgsConstructor
    public static class Post{

        String name;

        String password;

        String sex;

        String companyName;

        Long companyType;

        Long companyLocation;

    }
}


