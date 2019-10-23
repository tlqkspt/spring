package com.company.app.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.app.board.BoardVO;
import com.company.app.board.service.BoardService;

@Controller
public class BoardController {
	
	
	@Autowired BoardService boardService;
	
	//ajax테스트
	@RequestMapping("/boardClient")
	public String boardClient() {
		return "board/boardClient";
	}
	
	
	
	//컨트롤러 리턴값 String 으로 넘어가야됨
	//단건조회
	@RequestMapping("/getBoard")
	public String getBoard(BoardVO vo, Model model) {	//알아서담긴다?	//뷰페이지 값넘겨줄때 model
		model.addAttribute("board",boardService.getBoard(vo));
		return "board/getBoard";	// .jsp 붙이면안됨 알아서붙여준다
	}
	
	//전체조회
	@RequestMapping("/getBoardMap")
	public String getBoardMap(BoardVO vo, Model model) {
		model.addAttribute("boardList", boardService.getBoardMap(vo));
		return "board/getBoardList";
	}
	
	//등록폼
	@RequestMapping("/insertBoardForm")
	public String insertBoardForm() {
		return "board/insertBoard";
	}
	
	//등록처리
	@RequestMapping("/insertBoard")
	public String insertBoard(BoardVO vo) {
		boardService.insertBoard(vo);
		System.out.println(vo.getSeq()+ vo.getMsg() +" 번 글이 등록되었습니다.");
		return "redirect:/getBoardMap";
	}
	
	//단건삭제
	@RequestMapping("/deleteBoardList")
	public String deleteBoardList(BoardVO vo) {
		boardService.deleteBoardList(vo);
		return "redirect:/getBoardMap";		//목록으로 이동
	}
	
}
