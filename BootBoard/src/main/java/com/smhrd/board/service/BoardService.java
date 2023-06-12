package com.smhrd.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.board.domain.Board;
import com.smhrd.board.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	private BoardMapper mapper;
	
	public int write(Board b) {
		return mapper.write(b);
	}

	public List<Board> boardList() {
		// TODO Auto-generated method stub
		return mapper.boardList();
	}
	
	public Board content(int idx) {
		return mapper.content(idx);
	}
	
	public void delete(int idx) {
		mapper.delete(idx);
	}
	
	
	
	
	
}
