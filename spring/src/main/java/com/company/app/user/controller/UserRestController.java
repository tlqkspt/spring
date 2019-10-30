package com.company.app.user.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.user.UserVO;
import com.company.app.user.service.UserService;

@RestController
public class UserRestController {
	
	@Autowired UserService userService;
	
	//목록
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<UserVO> getUser(UserVO vo, Model model) {
		//model.addAttribute("user", userService.getUser(vo));
		return userService.getUserList(vo);
	}
	
	//등록
	@RequestMapping(value="/users"
				,method = RequestMethod.POST		
				,consumes="application/json"	)	//넘겨받는모든값이 제이슨	//제이슨들어가면 반드시@RequestBody써줘야함
	public Map insertUser(@RequestBody UserVO vo, Model model) {
		userService.insertUser(vo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("user", true);
		return map;
	}
	
	//조회
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public UserVO getUser(@PathVariable String id, UserVO vo, Model model ) {
		vo.setId(id);;
		return userService.getUser(vo);
	}
	
	//삭제
	@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	public Map deleteUser(@PathVariable String id, UserVO vo, Model model) {
		vo.setId(id);
		userService.deleteUser(vo);
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("result", Boolean.TRUE);
		
		return Collections.singletonMap("result", true);
	}
	
	//수정
	@RequestMapping(value="/users", method=RequestMethod.PUT
					,consumes="application/json")	//consumes 파라미터 받을거있을때
	public UserVO updateUser(@RequestBody UserVO vo, Model model) {
		userService.updateUser(vo);
		return vo;
	}
	
	
	//부서별인원(차트)
	@RequestMapping("getEmpCnt")	//매서드 기본은 get //value 하나만있으면 value 생략가능	//
	// @ResponseBody	//그냥 컨트롤러 일때 써줘야함	RestController 는 안써줘도된다
	public List<Map<String, Object>> getEmpCnt() {
		return userService.getEmpCnt();
	}
	
	
}
