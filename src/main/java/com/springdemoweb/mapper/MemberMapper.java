package com.springdemoweb.mapper;

import java.util.HashMap;

import com.springdemoweb.vo.Member;

//이 인터페이스의 패키지와 인터페이스 이름은  member-mapper.xml 파일의 namespace 영역과 일치
public interface MemberMapper {

	//메서드는 member-mapper.xml 파일의 Mapping 요소(select, insert 등)와 일치
	void insertMember(Member member);
	Member selectMemberByIdAndPasswd(HashMap<String, Object> params);
	
}
