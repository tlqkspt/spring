package com.company.app;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.app.board.BoardVO;
import com.company.app.board.service.BoardService;
import com.company.app.board.service.impl.BoardDAOSpring;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/*-context.xml")	//변경
public class BoardDAOClient {
	
	@Autowired BoardService boardService;
	
	@Ignore @Test
	public void insertBoardTest() {
		BoardVO vo = new BoardVO();
		vo.setTitle("mybatis jdbc test");
		vo.setContent("mybatis jdbc 내용");
		vo.setWriter("김길동");
		boardService.insertBoard(vo);
	}
	
	@Test
	public void getBoardList() {
		System.out.println(boardService.getBoardList(null));
	}
	
	@Test
	public void getBoard() {
		BoardVO vo = new BoardVO();
		vo.setSeq(1);
		System.out.println(boardService.getBoard(vo));
	}
}
