package com.company.app.board.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.company.app.board.BoardVO;

@Repository
public class BoardDAOJpa {
	
	@PersistenceContext		//인젝션(주입) = autowired 같은역할
	private EntityManager em;
	
	
	public void insertBoard(BoardVO vo) {
		System.out.println("jpa insertboard 기능 처리");
		em.persist(vo);
	}

	public void deleteBoard(BoardVO vo) {
		System.out.println("jps deleteboard 기능 처리");
		em.remove(em.find(BoardVO.class, vo.getSeq() ) );
	}

	public void updateBoard(BoardVO vo) {
		System.out.println("jpa updateboard 기능 처리");
		em.merge(vo);	//수정
	}

	public BoardVO getBoard(BoardVO vo) {
		return em.find(BoardVO.class, vo.getSeq());	//자동으로 짜준다
	}

	public List<BoardVO> getBoardList() {
		System.out.println("jpa getBoardList 기능 처리");
		return em.createQuery("from BoardVO b order by b.seq desc").getResultList();		//테이블은 모름 vo 만 바라본다
	}

	public List<Map<String, Object>> getBoardMap(BoardVO vo) {
		return null;
	}

	public void deleteBoardList(BoardVO vo) {
	}
}
