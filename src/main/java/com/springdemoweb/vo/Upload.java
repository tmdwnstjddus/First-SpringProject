package com.springdemoweb.vo;

import java.sql.Date;
import java.util.ArrayList;

import lombok.Data;

@Data
public class Upload {

	private int uploadNo;
	private String bookCode;
	private String memberCode;
	private String title;
	private String uploader;
	private String content;
	private String memberName;
	private Date regDate;
	private String returnDate;
	private boolean deleted;
	private String returnCheck; 
	
	//Upload Table과 UploadFile Table 사이의 1:Many 관계를 구현하는 필드
	private ArrayList<UploadFile> files;
	
}

