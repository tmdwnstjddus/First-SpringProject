package com.springdemoweb.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;

import com.springdemoweb.mapper.MemberMapper;
import com.springdemoweb.vo.Member;

public class MemberDaoImpl implements MemberDao {

	private SqlSessionTemplate sessionTemplate;
	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	
	private MemberMapper memberMapper;
	public MemberMapper getMemberMapper() {
		return memberMapper;
	}
	public void setMemberMapper(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	@Override
	public void insertMember(Member member) {
			
//		sessionTemplate.insert(
//			"com.springdemoweb.mapper.MemberMapper.insertMember", member);
		
		memberMapper.insertMember(member);
		
	}
		
	@Override
	public Member selectMemberByIdAndPasswd(String id, String passwd) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("memberId", id);
		params.put("passwd", passwd);
		
//		Member member = sessionTemplate.selectOne(
//			"com.springdemoweb.mapper.MemberMapper.selectMemberByIdAndPasswd",
//			params);
		
		Member member = memberMapper.selectMemberByIdAndPasswd(params);
		
		return member;
		
	}
	
}




















