package com.smhrd.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.board.converter.ImageConverter;
import com.smhrd.board.converter.ImageToBase64;
import com.smhrd.board.domain.Board;
import com.smhrd.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;

	@GetMapping("/board")
	public String boardList(Model model) {
		
		
		model.addAttribute("list",service.boardList());
		/*
		 * for(Board b : boards) { System.out.println(b.getIdx()); }
		 */
		return "boardlist";
	}
	 
	@GetMapping("/board/writeform")
	public String writeform() {
		return "boardform";
	}
	
	//@RequestPart : multipart/form-data 에 특화된 어노테이션
	@PostMapping("/board/write")
	public String write(Board b , @RequestPart("photo") MultipartFile file ) {
		System.out.println(b.getTitle()+"," + b.getContent()+ "," + b.getWriter());
		System.out.println(file.getOriginalFilename());
		System.out.println(UUID.randomUUID().toString());
		
		String newFileName =  UUID.randomUUID().toString() + file.getOriginalFilename();
		//이미지 file -> 저장(지정된 경로에)
		try {
			file.transferTo(new File(newFileName));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		b.setImg(newFileName);
		//b(Board) -> title, content, writer, img(filename)
		//board table(DB) -> 랜덤숫자+파일 이름만 저장
		int cnt = service.write(b);
		System.out.println(cnt);
	
		if(cnt>0) {
			//boardlist.jsp
			return "redirect:/board";
		}
		else{
			//boardform.jsp
			return "redirect:/board/writeform";
		}
		
		
	}
	
	@GetMapping("/board/content/{idx}")
	public String content(@PathVariable("idx") int idx, Model model ) {
		
		Board b = service.content(idx);
		File file = new File("c:\\uploadimage\\" +b.getImg());
		
		ImageConverter<File, String> converter = new ImageToBase64();
		
		try {
			String fileStringValue = converter.convert(file);
			System.out.println(fileStringValue);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("board",b);
		return "boardcontent";
		
		
	}
	
	
	
	
	
	
	
	
	
}
