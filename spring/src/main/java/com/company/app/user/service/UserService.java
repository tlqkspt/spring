package com.company.app.user.service;

import java.util.List;
import java.util.Map;

import com.company.app.user.UserVO;

public interface UserService {
	public UserVO getUser(UserVO vo);
	public List<UserVO> getUserList(UserVO vo);
	public List<Map> getUserListMap(UserVO user);
	//등록
	public int insertUser(UserVO vo);
	//수정
	public int updateUser(UserVO vo);
	//삭제
	public int deleteUser(UserVO vo);
	
	//부서별인원
	public List<Map<String, Object>> getEmpCnt();
	
	//로그인 체크
	public UserVO login(UserVO vo);
}
