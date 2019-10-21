package com.company.app.user.service.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.app.user.UserVO;

@Repository
public class UserDAO {
	
	@Autowired SqlSessionTemplate mybatis;
	
	//단건조회
	public UserVO getUser(UserVO vo) {
		return mybatis.selectOne("userdao.getUser", vo);
	}
}
