package com.company.app.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.board.BoardVO;
import com.company.app.board.service.BoardService;

@RestController		//@Controller + @ResponseBody
public class BoardRestController {
	
	@Autowired BoardService boardService;
	
	@RequestMapping("/getBoardList.json")	//url맘대로정하면됨
	//@ResponseBody		//	@ResponseBody p.456
	public List<BoardVO> getBoardList() {		//json결과넘길땐 String 아니라 매서드리턴타입? 이랑같게
		return boardService.getBoardList(null);
	}
	
	@RequestMapping("/getBoard.json")
	public BoardVO getBoard(BoardVO vo) {
		return boardService.getBoard(vo);
	}
	
	/*
	 * @RequestMapping(value = "/ajaxInsertBoard.json", method = RequestMethod.POST)
	 * public BoardVO insertBoard(BoardVO vo) { boardService.insertBoard(vo); return
	 * boardService.getBoard(vo); }
	 */
	
	@RequestMapping(value = "/ajaxInsertBoard.json", 
					method = RequestMethod.POST,
					produces = "application/json" )	//넘겨주는거 받는거 일치해야됨
	@ResponseBody
	public List<BoardVO> insertBoard(@RequestBody List<BoardVO> vo) {
		System.out.println(vo);
		return vo; 
	}
}
