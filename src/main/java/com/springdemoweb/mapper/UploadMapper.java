package com.springdemoweb.mapper;

import java.util.HashMap;
import java.util.List;

import com.springdemoweb.vo.Upload;
import com.springdemoweb.vo.UploadFile;

public interface UploadMapper {

	List<Upload> selectUploads();
	
	Upload selectUploadByUploadNo(int uploadNo);
	
	List<UploadFile> selectUploadFilesByUploadNo(int uploadNo);
	
	UploadFile selectUploadFileByUploadFileNo(int uploadFileNo);
	
	void deleteUpload(int uploadNo);
	
	void insertUpload(Upload upload);
	void insertUpload2(Upload upload);
	
	void insertUploadFile(UploadFile file);
	
	void deleteUploadFile(int uploadFileNo);
	
	void updateUpload(Upload upload);
	
	List<Upload> selectList(HashMap<String, Object> params);

}
