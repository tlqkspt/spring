package com.company.app.board.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.app.board.BoardVO;

@Repository
public class BoardDAOMybatis {
	
	@Autowired SqlSessionTemplate mybatis;
	
	//등록
	public void insertBoard(BoardVO vo) {
		/*
		 * System.out.println("mybatis insertBoard()");
		 * mybatis.insert("BoardDAO.insertBoard", vo);
		 */
		System.out.println("mybatis insertBoard procedure()");
		mybatis.insert("BoardDAO.insertBoardProc", vo);
	}
	
	//단건조회
	public BoardVO getBoard(BoardVO vo) {
		return mybatis.selectOne("BoardDAO.getBoard", vo);
	}
	
	//전체조회
	public List<BoardVO> getBoardList(){
		return mybatis.selectList("BoardDAO.getBoardList");
	}
	
	public List<Map<String, Object>> getBoardMap(BoardVO vo){
		return mybatis.selectList("BoardDAO.getBoardMap", vo);
	}
	
	//선택삭제
	public void deleteBoardList(BoardVO vo) {
		mybatis.delete("BoardDAO.deleteBoardList", vo);
	}
	
}
