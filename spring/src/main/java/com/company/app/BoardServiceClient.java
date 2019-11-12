package com.company.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.company.app.board.BoardVO;

public class BoardServiceClient {
	
	//나중엔 이거다 스프링이해준다
	
	public static void main(String[] args) {
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAProject2"); 	// persistence-unit name 적어주면 읽는다
		
		
		EntityManager em = emf.createEntityManager();
		
		//트랜잭션 생성
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			///작업할곳
			BoardVO vo = new BoardVO();
			vo.setTitle("jpa test");
			vo.setWriter("관리자");
			vo.setContent("jpa 글 등록 테스트");
			
			
			em.persist(vo);		//등록해라
			//조회
			//vo.setSeq(2);
			//System.out.println( em.find(BoardVO.class, vo.getSeq()) );
			
			//삭제
			//em.remove(em.find(BoardVO.class, vo.getSeq()) );
			
			//전체조회
			TypedQuery<BoardVO> query = em.createQuery(
					"from BoardVO b where b.writer = :writer order by b.seq desc", BoardVO.class);	//JPQL	//  : 치환변수
			query.setParameter("writer", "관리자");			
			query.setFirstResult(0);
			query.setMaxResults(6);
			System.out.println( query.getResultList() );
						
			///작업끝
			tx.commit();			
		}catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
