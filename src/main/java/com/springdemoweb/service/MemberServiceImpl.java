package com.springdemoweb.service;

import com.springdemoweb.common.Util;
import com.springdemoweb.dao.MemberDao;
import com.springdemoweb.vo.Member;


public class MemberServiceImpl implements MemberService {
	

	private MemberDao memberDao;
	public MemberDao getMemberDao() {
		return memberDao;
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public void registerMember(Member member) {
		
		String passwd = Util.getHashedString(member.getPasswd(), "SHA-256"); //패스워드 암호화 (복원불가능)
		member.setPasswd(passwd);
		
		memberDao.insertMember(member);
	}

	@Override
	public Member findMemberByIdAndPasswd(String memberId, String passwd) {

		passwd = Util.getHashedString(passwd, "SHA-256"); //암호화
		Member member = memberDao.selectMemberByIdAndPasswd(memberId, passwd);

		return member;
	}

}
