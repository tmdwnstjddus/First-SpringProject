package com.springdemoweb.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.springdemoweb.common.Util;
import com.springdemoweb.dao.UploadDao;
import com.springdemoweb.dao.UploadDaoImpl;
import com.springdemoweb.service.UploadService;
import com.springdemoweb.view.DownloadView;
import com.springdemoweb.vo.Upload;
import com.springdemoweb.vo.UploadFile;

@Controller
@RequestMapping(path = "/upload/")
public class UploadController {
	
	@Autowired
	@Qualifier("uploadService")
	private UploadService uploadService;
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String list(String keyField, String keyWord, Model model) {
		
	    
		//�뜲�씠�꽣踰좎씠�뒪�뿉�꽌 �옄猷� 紐⑸줉 議고쉶
		List<Upload> upload = uploadService.selectList(keyField, keyWord);
		
		//議고쉶 寃곌낵瑜� request 媛앹껜�뿉 ���옣 (JSP�뿉�꽌 �궗�슜�븷 �닔 �엳�룄濡�)
		model.addAttribute("upload", upload);
		
		return "upload/list"; // "/WEB-INF/views/" + upload/list + ".jsp"
	}
	
	@RequestMapping(path = "/booklist", method = RequestMethod.GET)
	public String booklist(Model model) {

		List<Upload> uploads = uploadService.findUploadList();
		
		//議고쉶 寃곌낵瑜� request 媛앹껜�뿉 ���옣 (JSP�뿉�꽌 �궗�슜�븷 �닔 �엳�룄濡�)
		model.addAttribute("uploads", uploads);
		
		return "upload/booklist"; // "/WEB-INF/views/" + upload/list + ".jsp"
	}

	
	@RequestMapping(path = "/write", method = RequestMethod.GET)
	public String writeForm() {
		
		return "upload/write"; // "/WEB-INF/views/" + upload/write + ".jsp"
	}
	
	@RequestMapping(path = "/write", method = RequestMethod.POST)
	public String write(MultipartHttpServletRequest req, Upload upload) {
		
		MultipartFile mf = req.getFile("attach");
		if (mf != null) {
			
			ServletContext application = req.getServletContext();
			String path = application.getRealPath("/upload-files");
			
			String userFileName = mf.getOriginalFilename();
			if (userFileName.contains("\\")) { // iexplore 寃쎌슦
				//C:\AAA\BBB\CCC.png -> CCC.png 
				userFileName = userFileName.substring(userFileName.lastIndexOf("\\") + 1);
			}
			String savedFileName = Util.makeUniqueFileName(userFileName);
			
			try {
				mf.transferTo(new File(path, savedFileName)); //�뙆�씪 ���옣
								
				UploadFile uploadFile = new UploadFile();
				uploadFile.setUserFileName(userFileName);
				uploadFile.setSavedFileName(savedFileName);
				//uploadFile.setUploadNo(newUploadNo);
				//uploadService.registerUploadFile(uploadFile);
				ArrayList<UploadFile> files = new ArrayList<UploadFile>();
				files.add(uploadFile);
				upload.setFiles(files);
				
				//�뜲�씠�꽣 ���옣				
				uploadService.registerUpload(upload);
				
				
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return "redirect:list?keyField=all&keyWord=";
	}
	
	@RequestMapping(path = "/detail", method = RequestMethod.GET)
	public String detail(@RequestParam(name="uploadno") Integer uploadNo, Model model) {
		
		System.out.println(uploadNo);
		
		Upload upload = uploadService.findUploadByUploadNo(uploadNo);
		if (upload == null) { //uploadno媛� �쑀�슚�븯吏� �븡�� 寃쎌슦 (�뜲�씠�꽣踰좎씠�뒪�뿉 �뾾�뒗 踰덊샇�씤 寃쎌슦)
			return "redirect:list";
		}		
		List<UploadFile> files = uploadService.findUploadFilesByUploadNo(uploadNo);
		upload.setFiles((ArrayList<UploadFile>)files);
		
		model.addAttribute("upload", upload);
		
		return "upload/detail"; // "/WEB-INF/views/" + upload/detail + ".jsp"
	}
	
	//@PathVariable : �슂泥� 寃쎈줈�쓽 {}遺�遺꾩쓣 �뜲�씠�꽣濡� �씫�뒗 annotation
	@RequestMapping(path = "/detail2/{uploadNo}", method = RequestMethod.GET)
	public String detail2(@PathVariable int uploadNo, Model model) {

		Upload upload = uploadService.findUploadByUploadNo(uploadNo);
		if (upload == null) { //uploadno媛� �쑀�슚�븯吏� �븡�� 寃쎌슦 (�뜲�씠�꽣踰좎씠�뒪�뿉 �뾾�뒗 踰덊샇�씤 寃쎌슦)
			return "redirect:list";
		}		
		List<UploadFile> files = uploadService.findUploadFilesByUploadNo(uploadNo);
		upload.setFiles((ArrayList<UploadFile>)files);
		
		model.addAttribute("upload", upload);
		
		return "upload/detail"; // "/WEB-INF/views/" + upload/detail + ".jsp"
	}
	
	//@PathVariable : �슂泥� 寃쎈줈�쓽 {}遺�遺꾩쓣 �뜲�씠�꽣濡� �씫�뒗 annotation
	@RequestMapping(path = "/download/{fileNo}", method = RequestMethod.GET)
	public View download(@PathVariable int fileNo, Model model) {
		
		UploadFile file = uploadService.findUploadFileByUploadFileNo(fileNo);
		if (file == null) {
			return new RedirectView("/upload/list");
		}
		
		model.addAttribute("file", file);
		
		DownloadView v = new DownloadView();
		return v;
	}
	
	//@PathVariable : �슂泥� 寃쎈줈�쓽 {}遺�遺꾩쓣 �뜲�씠�꽣濡� �씫�뒗 annotation
	@RequestMapping(path = "/delete/{uploadNo}", method = RequestMethod.GET)
	public String delete(@PathVariable int uploadNo) {

		uploadService.deleteUpload(uploadNo);
		
		return "redirect:/upload/list?keyField=all&keyWord="; // new RedirectView("/upload/list");
	}
	
	//@PathVariable : �슂泥� 寃쎈줈�쓽 {}遺�遺꾩쓣 �뜲�씠�꽣濡� �씫�뒗 annotation
	@RequestMapping(path = "/update/{uploadNo}", method = {RequestMethod.GET, RequestMethod.POST})

	public String updateForm(@PathVariable int uploadNo, Model model) {

		Upload upload = uploadService.findUploadByUploadNo(uploadNo);
		if (upload == null) { //uploadno媛� �쑀�슚�븯吏� �븡�� 寃쎌슦 (�뜲�씠�꽣踰좎씠�뒪�뿉 �뾾�뒗 踰덊샇�씤 寃쎌슦)
			return "redirect:list";
		}		
		List<UploadFile> files = uploadService.findUploadFilesByUploadNo(uploadNo);
		upload.setFiles((ArrayList<UploadFile>)files);
		
		model.addAttribute("upload", upload);

		
		return "upload/update";
	}
	
	//@PathVariable : �슂泥� 寃쎈줈�쓽 {}遺�遺꾩쓣 �뜲�씠�꽣濡� �씫�뒗 annotation
	@RequestMapping(path = "/delete-file/{uploadNo}/{fileNo}", method = RequestMethod.GET)
	public String deleteFile(
			@PathVariable int uploadNo, @PathVariable int fileNo, Model model) {

		UploadFile file = uploadService.findUploadFileByUploadFileNo(fileNo);
		//�뙆�씪 �궘�젣
		File f = new File(file.getSavedFileName());
		if (f.exists()) {
			f.delete();
		}		
		//�뜲�씠�꽣踰좎씠�뒪�뿉�꽌 �뙆�씪 �뜲�씠�꽣 �궘�젣		
		uploadService.deleteUploadFile(fileNo);
		
		return "redirect:/upload/update/" + uploadNo;
	}

	//@PathVariable : �슂泥� 寃쎈줈�쓽 {}遺�遺꾩쓣 �뜲�씠�꽣濡� �씫�뒗 annotation
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public String updateForm(MultipartHttpServletRequest req, Upload upload) {

		MultipartFile mf = req.getFile("attach");
		if (mf != null) {
			
			ServletContext application = req.getServletContext();
			String path = application.getRealPath("/upload-files");
			
			String userFileName = mf.getOriginalFilename();
			if (userFileName.contains("\\")) { // iexplore 寃쎌슦
				//C:\AAA\BBB\CCC.png -> CCC.png 
				userFileName = userFileName.substring(userFileName.lastIndexOf("\\") + 1);
			}
			String savedFileName = Util.makeUniqueFileName(userFileName);
			
			try {
				mf.transferTo(new File(path, savedFileName)); //�뙆�씪 ���옣
				
				//�뜲�씠�꽣 ���옣				
				uploadService.updateUpload(upload);
				
				UploadFile uploadFile = new UploadFile();
				uploadFile.setUserFileName(userFileName);
				uploadFile.setSavedFileName(savedFileName);
				uploadFile.setUploadNo(upload.getUploadNo());
				uploadService.registerUploadFile(uploadFile);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return "redirect:/upload/detail?uploadno=" + upload.getUploadNo();
	}
	
}



















