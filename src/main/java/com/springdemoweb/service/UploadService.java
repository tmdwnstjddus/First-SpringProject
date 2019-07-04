package com.springdemoweb.service;

import java.util.ArrayList;
import java.util.List;

import com.springdemoweb.vo.Upload;
import com.springdemoweb.vo.UploadFile;


public interface UploadService {

	int registerUpload(Upload upload);
	
	void registerUploadFile(UploadFile uploadFile);
	
	List<Upload> findUploadList();
	
	Upload findUploadByUploadNo(int uploadNo);
	
	List<UploadFile> findUploadFilesByUploadNo(int uploadNo);
	
	UploadFile findUploadFileByUploadFileNo(int uploadFileNo);
	
	void deleteUpload(int uploadNo);
	
	void deleteUploadFile(int fileNo);
	
	void updateUpload(Upload upload);

	List<Upload> selectList(String keyField, String keyWord);
}
