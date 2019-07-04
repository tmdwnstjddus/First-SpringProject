package com.springdemoweb.service;

import com.springdemoweb.vo.Member;

public interface MemberService {

	void registerMember(Member member);
	
	Member findMemberByIdAndPasswd(String memberId, String passwd);
	
	
}
