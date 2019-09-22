package com.yi.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yi.domain.Criteria;
import com.yi.domain.MemberPicture;
import com.yi.domain.PageMaker;
import com.yi.domain.PersonalData;
import com.yi.service.PictureServiceDao;
import com.yi.util.UploadFileUtils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PictureController {
	
	private static final Logger logger = LoggerFactory.getLogger(PictureController.class);
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	
	@Autowired
	PictureServiceDao pService;
	
	@RequestMapping(value="displayFile", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<byte[]> displayFile(String filename) throws IOException{
		logger.info("===================displayFile, filename="+filename);
		
		String formatName = filename.substring(filename.lastIndexOf(".")+1);//확장자만 뽑아냄
		MediaType mType = null;
		ResponseEntity<byte[]> entity;
		if(formatName.equalsIgnoreCase("jpg")) {
			mType = MediaType.IMAGE_JPEG;
		}else if(formatName.equalsIgnoreCase("gif")) {
			mType = MediaType.IMAGE_GIF;
		}else if(formatName.equalsIgnoreCase("png")) {
			mType = MediaType.IMAGE_PNG;
		}
		InputStream in = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath+"/"+filename);
			headers.setContentType(mType);
		
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
			
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		logger.info("==================join");
		
		return "Join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPost(MemberPicture member, RedirectAttributes ratt) {
		logger.info("==================joinPost");
		logger.info("==================member="+member.toString());
		
		pService.insertMember(member);
		
		ratt.addFlashAttribute("result", "success");
		return "redirect:/login";
	}
	
	
	
	
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> check(MemberPicture member) {
		logger.info("==================중복체크");
		logger.info("==================member="+member.toString());
		ResponseEntity<String> entity = null;
		MemberPicture mem = pService.selectMember(member);
		if(mem!=null) {
			logger.info("==================중복");
			entity = new ResponseEntity<String>("duplicate", HttpStatus.OK);
		}else {
			logger.info("==================사용가능");
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		logger.info("==================login");
		
		return "Login";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("==================logout");
		session.invalidate();
		return "Login";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(PersonalData data) {
		logger.info("==================delete");
		pService.deleteData(data);
		UploadFileUtils.deletefile(uploadPath, data.getFile());
		return "redirect:/List";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(PersonalData data, HttpServletRequest req) {
		logger.info("==================loginPost");
		logger.info("==================id="+data.toString());
		HttpSession session = req.getSession();
		session.setAttribute("AUTH", data.getId());
		return "redirect:/List";
	}
	@RequestMapping(value = "/List", method = RequestMethod.GET)
	public String List(HttpSession session, Model model, Criteria cri) {
		logger.info("==================List");
		String id = (String) session.getAttribute("AUTH");
		PersonalData data = new PersonalData();
		data.setId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("cri", cri);
		
		List<PersonalData> list = pService.selectMyList(map);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(pService.selectList(data).size());
		
		for(PersonalData l : list) {
			String originFileName = l.getFile().substring(0,12) + l.getFile().substring(14);
			l.setOriginFile(originFileName);
			String[] a = originFileName.split("_");
			String origin = a[a.length-1];
			l.setOriginFileName(origin);
			logger.info("=================="+origin);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("page", pageMaker);
		return "List";
	}
	
	
	@RequestMapping(value = "/check2", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> check2(MemberPicture member) {
		logger.info("==================check2");
		ResponseEntity<String> entity = null;
		MemberPicture mem = pService.selectMember(member);
		
		if(mem==null || !mem.getPass().equals(member.getPass())) {
			entity = new ResponseEntity<String>("fail", HttpStatus.OK);
		}else if(mem!=null || mem.getPass().equals(member.getPass())) {
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {
		logger.info("==================upload");
		
		return "upload";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadPost(List<MultipartFile> picture, HttpSession session) throws IOException, Exception {
		logger.info("==================uploadPost");
		for(MultipartFile p : picture) {
			logger.info("==================picture="+p.getOriginalFilename());
			String id = (String) session.getAttribute("AUTH");
			String file = UploadFileUtils.uploadFile(uploadPath, p.getOriginalFilename(), p.getBytes());
			Date date = new Date();
			PersonalData data = new PersonalData(id, date, file);
			pService.insertData(data);
		}
		
		
		return "redirect:/List";
	}
	
	
}
