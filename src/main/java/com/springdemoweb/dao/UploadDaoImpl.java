package com.springdemoweb.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import com.springdemoweb.mapper.UploadMapper;
import com.springdemoweb.vo.Upload;
import com.springdemoweb.vo.UploadFile;

public class UploadDaoImpl implements UploadDao {
	
	//JDBC의 구조 코드를 단순하게 만드는 스프링의 클래스
	private JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private SqlSessionTemplate sessionTemplate;
	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	
	private UploadMapper uploadMapper;
	public UploadMapper getUploadMapper() {
		return uploadMapper;
	}
	public void setUploadMapper(UploadMapper uploadMapper) {
		this.uploadMapper = uploadMapper;
	}
	
	//반환 값은 UPLOAD 테이블에 새로 INSERT된 데이터의 UPLOADNO (NEXTVAL한 후의 CURRVAL)
	@Override
	public int insertUpload(Upload upload) {
		
		//before insert : upload.getUploadNo() ==> 0 //비어 있는 값
		
//		sessionTemplate.insert(
//			"com.springdemoweb.mapper.UploadMapper.insertUpload", upload);
		
		uploadMapper.insertUpload(upload);
		
		//after insert : upload.getUploadNo() ==> new value
		
		return upload.getUploadNo();
		
	}
	
	@Override
	public void insertUploadFile(UploadFile file) {
		
//		sessionTemplate.insert(
//			"com.springdemoweb.mapper.UploadMapper.insertUploadFile", file);
		uploadMapper.insertUploadFile(file);
		
	}
	
	@Override
	public ArrayList<Upload> selectUploads() {
		
		List<Upload> uploads = 
			//sessionTemplate.selectList("com.springdemoweb.mapper.UploadMapper.selectUploads");
			uploadMapper.selectUploads();
		
		return (ArrayList<Upload>)uploads;
		
	}

	@Override
	public Upload selectUploadByUploadNo(int uploadNo) {
		
//		Upload upload = sessionTemplate.selectOne(
//			"com.springdemoweb.mapper.UploadMapper.selectUploadByUploadNo", uploadNo);
		Upload upload = uploadMapper.selectUploadByUploadNo(uploadNo);
		
		return upload; //호출한 곳으로 조회한 데이터를 반환
	}

	@Override
	public ArrayList<UploadFile> selectUploadFilesByUploadNo(int uploadNo) {
		
//		List<UploadFile> files = sessionTemplate.selectList(
//			"com.springdemoweb.mapper.UploadMapper.selectUploadFilesByUploadNo", uploadNo);
		List<UploadFile> files = uploadMapper.selectUploadFilesByUploadNo(uploadNo);
		
		return (ArrayList<UploadFile>)files;
	}

	@Override
	public UploadFile selectUploadFileByUploadFileNo(int fileNo) {
		
//		UploadFile file = sessionTemplate.selectOne(
//			"com.springdemoweb.mapper.UploadMapper.selectUploadFileByUploadFileNo", fileNo);
		UploadFile file = uploadMapper.selectUploadFileByUploadFileNo(fileNo);
				
		return file; //호출한 곳으로 조회한 데이터를 반환
	}

	@Override
	public void deleteUpload(int uploadNo) {
		
//		sessionTemplate.delete(
//			"com.springdemoweb.mapper.UploadMapper.deleteUpload", uploadNo);
		
		uploadMapper.deleteUpload(uploadNo);		
				
	}

	@Override
	public void deleteUploadFile(int fileNo) {
		
//		sessionTemplate.delete(
//				"com.springdemoweb.mapper.UploadMapper.deleteUploadFile", 
//				fileNo);
		
		uploadMapper.deleteUploadFile(fileNo);
		
	}

	@Override
	public void updateUpload(Upload upload) {
		
//		sessionTemplate.update(
//				"com.springdemoweb.mapper.updateUpload",
//				upload);
		
		uploadMapper.updateUpload(upload);
		
	}
	@Override
	public ArrayList<Upload> listUpload(String keyField, String keyWord) {


		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("keyField", keyField);
		params.put("keyWord", String.format("%%%s%%", keyWord));
		List<Upload> upload = uploadMapper.selectList(params);
		
        return (ArrayList<Upload>) upload;
		
	}
	
	
}




















