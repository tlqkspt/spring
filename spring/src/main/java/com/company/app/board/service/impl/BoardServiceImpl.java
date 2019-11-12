package com.company.app.board.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.board.BoardVO;
import com.company.app.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{

	//@Autowired BoardDAOMybatis boardDAO;
	@Autowired BoardDAOJpa boardDAO;
	
	@Override
	public void insertBoard(BoardVO vo) {
		//서비스 메서드 단위로 트랜잭션 발생
		boardDAO.insertBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		
		return boardDAO.getBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		
		return boardDAO.getBoardList();
	}

	@Override
	public List<Map<String, Object>> getBoardMap(BoardVO vo) {
	
		return boardDAO.getBoardMap(vo);
	}

	@Override
	public void deleteBoardList(BoardVO vo) {
		boardDAO.deleteBoardList(vo);		
	}

}
