package com.smhrd.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.myapp.domain.Member;

@Mapper
public interface MemberMapper {
	
	public List<Member> memberList();
		
}
