package com.springdemoweb.dao;

import java.util.ArrayList;
import java.util.List;

import com.springdemoweb.vo.Upload;
import com.springdemoweb.vo.UploadFile;

public interface UploadDao {
	
	public int insertUpload(Upload upload);
	
	public void insertUploadFile(UploadFile file);
	
	public ArrayList<Upload> selectUploads();
	
	public Upload selectUploadByUploadNo(int uploadNo);
	
	public ArrayList<UploadFile> selectUploadFilesByUploadNo(int uploadNo);
	
	public UploadFile selectUploadFileByUploadFileNo(int fileNo);
	
	public void deleteUpload(int uploadNo);
	
	public void deleteUploadFile(int fileNo);
	
	public void updateUpload(Upload upload);

	public ArrayList<Upload> listUpload(String keyField, String keyWord);


}
