package com.company.app.board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.company.app.board.BoardVO;
import com.company.app.board.service.BoardService;

@Controller	//@Component 상속
//1. 컨테이너에 빈 생성해서 등록
//2. 디서패처 서블릿이 인식할 수 있는 컨트롤러로 변경 Controller 클래스의 handleRequest메서드 형식으로 바꿔줌
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired BoardService boardService;
	
	@ModelAttribute("boardType")
	public Map<String, String> getBoardType() {
		Map<String, String> boardType = new HashMap<String, String>();
		boardType.put("일반","T1");
		boardType.put("공지사항","T2");
		boardType.put("첨부","T3");
		return boardType;
	}
	
	//ajax테스트
	@RequestMapping("/boardClient")
	public String boardClient() {
		return "board/boardClient";
	}
	
	
	
	//컨트롤러 리턴값 String 으로 넘어가야됨
	//단건조회
//	@RequestMapping("/getBoard")
//	public String getBoard(BoardVO vo, Model model) {	//알아서담긴다?	//뷰페이지 값넘겨줄때 model 에저장
//		model.addAttribute("board",boardService.getBoard(vo));
//		return "board/getBoard";	// .jsp 붙이면안됨 알아서붙여준다
//	}
		@RequestMapping("/getBoard")
	public String getBoard(HttpServletRequest request, 
						   Model model,
						   @RequestParam int seq) {	//VO 안쓸때 //@requestparam 안넘어오면 에러남
		BoardVO vo = new BoardVO();
		//vo.setSeq(Integer.parseInt(request.getParameter("seq")));
		
		//int seq = Integer.parseInt(request.getParameter("seq"));
		vo.setSeq(seq);
		
		model.addAttribute("board",boardService.getBoard(vo));
		return "board/getBoard";	// .jsp 붙이면안됨 알아서붙여준다
	}
	
	
	
	//전체조회
//	@RequestMapping("/getBoardMap")
//	public String getBoardMap(BoardVO vo, Model model) {
//		model.addAttribute("boardList", boardService.getBoardMap(vo));
//		return "board/getBoardList";
//	}
	@RequestMapping("/getBoardList")
	public ModelAndView getBoardMap(BoardVO vo
			, @RequestParam(required = false, value = "title", defaultValue = "board") String titleValue) {	//모델앤뷰 사용
		
		//System.out.println(titleValue+"============");
		logger.info(titleValue+"=============");
		
		ModelAndView mv = new ModelAndView();
		
//		mv.addObject("boardList", boardService.getBoardMap(vo));
		mv.addObject("boardList", boardService.getBoardList(vo));
		mv.setViewName("board/getBoardList");
		return mv;
	}
	
	//등록폼
	@RequestMapping("/insertBoardForm")
	public String insertBoardForm(@ModelAttribute("board") BoardVO vo) {
//		위로감
//		Map<String, String> boardType = new HashMap<String, String>();
//		boardType.put("일반","T1");
//		boardType.put("공지사항","T2");
//		boardType.put("첨부","T3");
//		model.addAttribute("boardType", boardType);
		
		return "board/insertBoard";
	}
	
	//수정폼
	@RequestMapping("/updateBoardForm")
	public String updateBoardForm(BoardVO vo, Model model) {
		model.addAttribute("board", boardService.getBoard(vo));
		return "board/insertBoard";
	}
	
	
	//등록처리
	@RequestMapping("/insertBoard")
	public String insertBoard(BoardVO vo, 
							MultipartHttpServletRequest multipart,
							HttpServletRequest request) {
		//첨부파일 업로드하고 파일명 조회
//		MultipartFile multipartFile = vo.getUploadFile();
		MultipartFile multipartFile = multipart.getFile("uploadFile");	//파일여러개면 getFiles s붙여서쓰셈
		
		if(multipartFile !=  null && multipartFile.getSize() > 0) {	//첨부파일 있으면
			//파일명
			String fileName = multipartFile.getOriginalFilename();	//이름담아서
			vo.setUploadFilename(fileName);							//vo에 set

			//업로드 폴더로 파일 이동
			String path = request.getSession().getServletContext().getRealPath("/resources/image");
			System.out.println("================"+path);
	        File imageFile = new File(path, fileName);	//new File(servletequest.getServletContext().getRealPath("/image"), fileName);
	        try {
	        	//파일중복되면 이름바꿔줘야함
	        	
	            multipartFile.transferTo(imageFile);
	        } catch (IOException e){
	            e.printStackTrace();
	        }
		}
		
		//등록 수정 구분
		if( vo.getSeq() > 0 ) {
			boardService.updateBoard(vo);
		} else {
			boardService.insertBoard(vo);
		}
		
		System.out.println(vo.getSeq()+ vo.getMsg() +" 번 글이 등록되었습니다.");
		return "redirect:/getBoardMap";
	}
	
	//단건삭제
	@RequestMapping("/deleteBoardList")
	public String deleteBoardList(BoardVO vo) {
		boardService.deleteBoardList(vo);
		return "redirect:/getBoardMap";		//목록으로 이동
	}
	
	
	@RequestMapping("/download/{fileName:.+}")
    public void downloadPDFResource( HttpServletRequest request,
                                     HttpServletResponse response,
                                     @PathVariable("fileName") String fileName)
    {
      
        String dataDirectory = request.getSession().getServletContext().getRealPath("/resources/image/");
        Path file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file))
        {
            response.setContentType("application/octet-stream;charset=UTF-8");	// 다운로드타입? pdf면 application/pdf 여러개있음 찾아서쓰라
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try
            {
                Files.copy(file, response.getOutputStream());	//copy? (파일소스, response로?)
                response.getOutputStream().flush();		//  flush() 클라이언트로 내려보냄
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
	
}
