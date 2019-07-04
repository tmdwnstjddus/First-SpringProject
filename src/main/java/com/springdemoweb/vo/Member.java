package com.springdemoweb.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class Member {
	
	private String memberId;
	private String passwd;
	private String email;
	private String userType;
	private Date regDate;		//java.sql.Date or java.util.Date
	private boolean active;		//oracle에는 boolean 타입이 없지만 char(1) 자료형으로 java의 boolean 호환 가능

}
