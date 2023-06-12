package com.smhrd.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.board.domain.Board;
import com.smhrd.board.service.BoardService;

@RestController
public class BoardRestController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/board/delete/{idx}")
	public void delete(@PathVariable("idx") int idx) {
		
		service.delete(idx);
	}
	
	@GetMapping("/board/ajax")
	public List<Board> BoardList(){
		return service.boardList();
	}
}
