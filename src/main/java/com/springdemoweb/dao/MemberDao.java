package com.springdemoweb.dao;

import com.springdemoweb.vo.Member;

public interface MemberDao {

	void insertMember(Member member);

	Member selectMemberByIdAndPasswd(String id, String passwd);
}