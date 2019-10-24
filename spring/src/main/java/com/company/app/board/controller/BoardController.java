package com.company.app.board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
